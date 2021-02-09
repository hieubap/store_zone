package store.zone.service;

import org.springframework.http.ResponseEntity;
import store.zone.dto.FoodDTO;

public interface FoodService {
  ResponseEntity<?> search(FoodDTO dto);
  ResponseEntity<?> create(FoodDTO dto);
  ResponseEntity<?> update(Long id,FoodDTO dto) throws Exception;
  ResponseEntity<?> delete(Long id) throws Exception;
}
