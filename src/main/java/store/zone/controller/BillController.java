package store.zone.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.zone.dto.BillDTO;
import store.zone.dto.OrderDTO;
import store.zone.service.BillService;

@CrossOrigin
@RestController
@RequestMapping(value = "/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @GetMapping(value = "/search")
  public ResponseEntity<?> search(BillDTO billDTO){
    return billService.search();
  }

  @GetMapping(value = "/dashboard")
  public ResponseEntity<?> dashboard(
      @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
      @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to){
    return billService.dashboard(from, to);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> order(@RequestBody OrderDTO orderDTO) throws Exception {
    return billService.create(orderDTO);
  }

  @PutMapping(value = "/store/confirm")
  public ResponseEntity<?> storeConfirm(@Param(value = "id") Long id) throws Exception {
    return billService.storeConfirm(id);
  }

  @PutMapping(value = "/cancel")
  public ResponseEntity<?> cancelBill(@Param(value = "id") Long id) throws Exception {
    return billService.cancel(id);
  }

  @PutMapping(value = "/delivered")
  public ResponseEntity<?> deliveredBill(@Param(value = "id") Long id) throws Exception {
    return billService.delivered(id);
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<?> deleteBill(@Param(value = "id") Long id) throws Exception {
    return billService.delete(id);
  }
}
