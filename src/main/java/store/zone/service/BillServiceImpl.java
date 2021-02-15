package store.zone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import store.zone.dao.model.BillEntity;
import store.zone.dao.model.BillFoodEntity;
import store.zone.dao.model.FoodEntity;
import store.zone.dao.model.UserEntity;
import store.zone.dao.repository.BillFoodRepository;
import store.zone.dao.repository.BillRepository;
import store.zone.dao.repository.FoodRepository;
import store.zone.dao.repository.UserRepository;
import store.zone.dto.BillDTO;
import store.zone.dto.BillDTO.Bill_Food;
import store.zone.dto.FoodDTO;
import store.zone.dto.OrderDTO;
import store.zone.dto.ResponseDTO;
import store.zone.enums.BillStatus;
import store.zone.exception.BaseException;

@Service
public class BillServiceImpl implements BillService{
  @Autowired
  private BillRepository billRepository;

  @Autowired
  private BillFoodRepository billFoodRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FoodRepository foodRepository;

  @Override
  public ResponseEntity<?> search() {
    List<BillDTO> list = new ArrayList<>();
    List<BillEntity> entities = billRepository.findAll();

    for (BillEntity bill : entities){
      list.add(convertBillEntityToDTO(bill));
    }

    return new ResponseEntity<>(new ResponseDTO<>(list), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> create(OrderDTO dto) throws Exception {
    if (dto.getListFoodsOrder() == null){
      throw new BaseException(400,"listFoodsOrder is null",null);
    }
    if (dto.getUserId() == null){
      throw new BaseException(400,"userId is null",null);
    }
    for (Map.Entry<Long,Integer> id : dto.getListFoodsOrder().entrySet()){
      if (!foodRepository.existsById(id.getKey())){
        throw new BaseException(400,"id food is not exist",id);
      }
    }

    BillEntity billEntity = new BillEntity();
    UserEntity userEntity = userRepository.findById(dto.getUserId()).get();

    billEntity.setUser(userEntity);
    billEntity.setStatus(BillStatus.WAIT_STORE_CONFIRM);

    billRepository.save(billEntity);

    List<BillFoodEntity> billFoodEntities = new ArrayList<>();
    for (Map.Entry<Long,Integer> billFood : dto.getListFoodsOrder().entrySet()){
      BillFoodEntity billFoodEntity = new BillFoodEntity();

      billFoodEntity.setFood(foodRepository.findById(billFood.getKey()).get());
      billFoodEntity.setBill(billEntity);
      billFoodEntity.setNumber(billFood.getValue());

      billFoodRepository.save(billFoodEntity);
      billFoodEntities.add(billFoodEntity);
    }
    billEntity.setListFoods(billFoodEntities);

    return new ResponseEntity<>(new ResponseDTO<>(convertBillEntityToDTO(billEntity)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> update(Long id, OrderDTO dto) throws Exception {
    return null;
  }

  @Override
  public ResponseEntity<?> delete(Long id) throws Exception {
    if (!billRepository.existsById(id)) {
      throw new BaseException(400,"id bill is not exist",id);
    }
    billRepository.deleteById(id);
    return new ResponseEntity<>(new ResponseDTO<>("delete successful"), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> storeConfirm(Long id) throws Exception {
    BillEntity billEntity = billRepository.findById(id).get();

    if (billEntity.getStatus() == BillStatus.CANCEL){
      throw new BaseException(400,"bill just have cancel",convertBillEntityToDTO(billEntity));
    }
    if (billEntity.getStatus() == BillStatus.DELIVERED){
      throw new BaseException(400,"bill just have delivered",convertBillEntityToDTO(billEntity));
    }
    if (billEntity.getStatus() == BillStatus.STORE_CONFIRM){
      throw new BaseException(400,"bill just have confirm",convertBillEntityToDTO(billEntity));
    }

    billEntity.setStatus(BillStatus.STORE_CONFIRM);

    billRepository.save(billEntity);

    return new ResponseEntity<>(new ResponseDTO<>(convertBillEntityToDTO(billEntity)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> cancel(Long id) throws Exception {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.CANCEL);

    billRepository.save(billEntity);

    return new ResponseEntity<>(new ResponseDTO<>(convertBillEntityToDTO(billEntity)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> delivered(Long id) throws Exception {
    BillEntity billEntity = billRepository.findById(id).get();
    billEntity.setStatus(BillStatus.DELIVERED);

    billRepository.save(billEntity);

    return new ResponseEntity<>(new ResponseDTO<>(convertBillEntityToDTO(billEntity)), HttpStatus.OK);
  }

  public static BillDTO convertBillEntityToDTO(BillEntity entity){
    BillDTO dto = new BillDTO();
    dto.setId(entity.getId());
    long sum = 0;

    List<Bill_Food> listBillFood = new ArrayList<>();
    for (BillFoodEntity billFoodEntity : entity.getListFoods()){
      Bill_Food billFood = convertBillFoodEntityToDTO(billFoodEntity);

      sum += billFood.getPrice();

      listBillFood.add(billFood);
    }

    dto.setDate(entity.getCreatedDate());
    dto.setFoods(listBillFood);
    dto.setStatus(entity.getStatus().getString());
    dto.setUser(UserServiceImpl.convertUserEntityToDTO(entity.getUser()));
    dto.setTotal(sum);

    return dto;
  }
  public static Bill_Food convertBillFoodEntityToDTO(BillFoodEntity entity){
    Bill_Food billFood = new Bill_Food();

    FoodDTO foodDTO = new FoodDTO();
    foodDTO.setId(entity.getFood().getId());
    foodDTO.setName(entity.getFood().getName());
    foodDTO.setPrice(entity.getFood().getPrice());
    billFood.setFood(foodDTO);

    billFood.setNumber(entity.getNumber());
    billFood.setPrice(entity.getNumber()*entity.getFood().getPrice());

    return billFood;
  }
}
