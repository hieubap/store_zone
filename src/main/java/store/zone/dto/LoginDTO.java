package store.zone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
  private String username;
  private String email;
  private String phone;


  private String password;
}
