package store.zone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import store.zone.dto.ResponseDTO;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<?> handleException(BaseException ex) {
    ResponseDTO responseDTO = new ResponseDTO();
    responseDTO.setCode(ex.getCode());
    responseDTO.setMessage(ex.getMessage());
    responseDTO.setData(ex.getData());

    return new ResponseEntity<>(responseDTO,HttpStatus.valueOf(ex.getCode()));
  }
}
