package store.zone.dto;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Object;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO<T> {
  private Integer code;
  private String message;
  private T data;

  public ResponseDTO(T data){
    code = 200;
    message = "successful";
    this.data = data;
  }

  public ResponseDTO(String message, T data) {
    this.message = message;
    this.data = data;
  }

  public ResponseDTO(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
}
