package store.zone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import store.zone.dto.LoginDTO;
import store.zone.dto.UserDTO;
import store.zone.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  public UserService userService;

  @RequestMapping(value = "/login" , method = RequestMethod.POST)
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws Exception {
    return userService.login(loginDTO);
  }

  @GetMapping(value = "/search")
  public ResponseEntity<?> search(UserDTO userDTO){
    return userService.search(userDTO);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody UserDTO dto){
    return userService.register(dto);
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@Param(value = "id") Long id,UserDTO userDTO) throws Exception {
    return userService.update(id,userDTO);
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<?> delete(@Param(value = "id") Long id) throws Exception{
    return userService.delete(id);
  }
}