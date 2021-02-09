package store.zone.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import store.zone.dao.model.UserEntity;
import store.zone.dao.repository.UserRepository;
import store.zone.dto.LoginDTO;
import store.zone.dto.ResponseDTO;
import store.zone.dto.UserDTO;
import store.zone.exception.BaseException;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public ResponseEntity<?> search(UserDTO userDTO) {
    List<UserEntity> listEntities = userRepository.search(userDTO);
    List<UserDTO> listDto = new ArrayList<>();

    for (UserEntity entity : listEntities){
      UserDTO dto = new UserDTO();
      dto.setId(entity.getId());
      dto.setName(entity.getName());
      dto.setPhone(entity.getPhone());
      dto.setEmail(entity.getEmail());
      dto.setAddress(entity.getAddress());

      listDto.add(dto);
    }

    return new ResponseEntity<>(new ResponseDTO<>(listDto), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> login(LoginDTO loginDTO) throws Exception {
    if (loginDTO.getUsername() == null && loginDTO.getEmail() == null && loginDTO.getPhone() == null) {
      throw new BaseException(400,"username or email or phone is null.",loginDTO);
    }

    UserEntity userEntity = userRepository.getUser(loginDTO);

    if (!userEntity.getPassword().equals(loginDTO.getPassword())){
      throw new BaseException(400,"password is not exactly. check password",null);
    }

    UserDTO userDTO = new UserDTO();
    userDTO.setId(userEntity.getId());
    userDTO.setName(userEntity.getName());
    userDTO.setPhone(userEntity.getPhone());
    userDTO.setEmail(userEntity.getEmail());
    userDTO.setAddress(userEntity.getAddress());

    return new ResponseEntity<>(new ResponseDTO<>(userDTO),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> register(UserDTO userDTO) {

    UserEntity entity = new UserEntity();
    entity.setName(userDTO.getName());
    entity.setPhone(userDTO.getPhone());
    entity.setAddress(userDTO.getAddress());
    entity.setEmail(userDTO.getEmail());

    userRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> update(Long id, UserDTO userDTO) throws Exception {
    if (!userRepository.existsById(id)) {
      throw new BaseException(400,"id is not exist. check your id",id);
    }

    UserEntity entity = userRepository.findById(id).get();
    entity.setName(userDTO.getName());
    entity.setPhone(userDTO.getPhone());
    entity.setAddress(userDTO.getAddress());
    entity.setEmail(userDTO.getEmail());
    userRepository.save(entity);

    return new ResponseEntity<>(new ResponseDTO<>(entity),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> delete(Long id) throws Exception {
    if (!userRepository.existsById(id)) {
      throw new BaseException(400,"id is not exist. check your id",id);
    }

    userRepository.deleteById(id);
    ResponseDTO<?> responseDTO = new ResponseDTO(200,"delete id '" + id +"' successful",null);

    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }

  public static UserDTO convertUserEntityToDTO(UserEntity entity){
    UserDTO dto = new UserDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPhone(entity.getPhone());
    dto.setEmail(entity.getEmail());
    dto.setAddress(entity.getAddress());

    return dto;
  }
}
