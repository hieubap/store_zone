package store.zone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import store.zone.dto.LoginDTO;
import store.zone.service.UserService;

@CrossOrigin
@RestController
public class LoginController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login" , method = RequestMethod.POST)
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws Exception {
    return userService.login(loginDTO);
  }
}
