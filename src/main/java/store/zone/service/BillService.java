package store.zone.service;

import org.springframework.http.ResponseEntity;
import store.zone.dto.OrderDTO;

public interface BillService {
  public ResponseEntity<?> search();
  public ResponseEntity<?> create(OrderDTO dto) throws Exception;
  public ResponseEntity<?> update(Long id,OrderDTO dto) throws Exception;
  public ResponseEntity<?> delete(Long id) throws Exception;

  public ResponseEntity<?> storeConfirm(Long id) throws Exception;
  public ResponseEntity<?> cancel(Long id) throws Exception;
  public ResponseEntity<?> delivered(Long id) throws Exception;
}
