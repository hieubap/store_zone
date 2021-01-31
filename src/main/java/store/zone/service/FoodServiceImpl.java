package store.zone.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import store.zone.dao.model.FoodEntity;
import store.zone.dao.repository.FoodRepository;
import store.zone.dto.FoodDTO;
import store.zone.dto.ResponseDTO;
import store.zone.exception.BaseException;

@Service
public class FoodServiceImpl implements FoodService {
  @Autowired
  private FoodRepository foodRepository;

  @Override
  public ResponseEntity<?> search() {
    List<FoodDTO> listDto = new ArrayList<>();
    List<FoodEntity> list = foodRepository.findAll();

    for (FoodEntity entity : list){
      FoodDTO dto = new FoodDTO();
      dto.setName(entity.getName());
      dto.setPrice(entity.getPrice());
      listDto.add(dto);
    }

    return new ResponseEntity<>(new ResponseDTO<>(listDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> create(FoodDTO dto) {
    FoodEntity entity = new FoodEntity();
    entity.setName(dto.getName());
    entity.setPrice(dto.getPrice());

    foodRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> update(Long id, FoodDTO dto) throws Exception {
    if (!foodRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",dto);
    }
    FoodEntity entity = foodRepository.findById(id).get();
    entity.setName(dto.getName());
    entity.setPrice(dto.getPrice());
    foodRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> delete(Long id) throws Exception {
    if (!foodRepository.existsById(id)){
      throw new BaseException(400,"id is not exist. check your id",id);
    }
    foodRepository.deleteById(id);
    ResponseDTO<?> responseDTO = new ResponseDTO(200,"delete id '" + id +"' successful",null);

    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }
}
