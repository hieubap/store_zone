package store.zone.service;

import org.springframework.http.ResponseEntity;
import store.zone.dto.LoginDTO;
import store.zone.dto.UserDTO;

public interface UserService {
  public ResponseEntity<?> login(LoginDTO loginDTO) throws Exception;
  public ResponseEntity<?> register(UserDTO userDTO);
  public ResponseEntity<?> search(UserDTO userDTO);
  public ResponseEntity<?> update(Long id,UserDTO userDTO) throws Exception;
  public ResponseEntity<?> delete(Long id) throws Exception;
}
