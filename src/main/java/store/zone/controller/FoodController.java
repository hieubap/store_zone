package store.zone.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/food")
public class FoodController {
  @GetMapping(value = "/search")
  public ResponseEntity<?>search() {
    Map<String,String> map = new HashMap<>();
    map.put("name","Bánh Mì");
    map.put("price","10000");

    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
