package store.zone.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {
  private int code;
  private String message;
  private Object data;

  public BaseException(int code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

}
