package store.zone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.zone.dto.FoodDTO;
import store.zone.service.FoodService;

@RestController
@RequestMapping(value = "/food")
public class FoodController {

  @Autowired
  private FoodService foodService;

  @GetMapping(value = "/search")
  public ResponseEntity<?> search() {
    return foodService.search();
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody FoodDTO foodDTO){
    return foodService.create(foodDTO);
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody FoodDTO dto,@Param(value = "id") Long id)
      throws Exception {
    return foodService.update(id, dto);
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<?> delete(@Param(value = "id") Long id) throws Exception {
    return foodService.delete(id);
  }
}
