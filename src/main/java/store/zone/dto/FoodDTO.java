package store.zone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FoodDTO {
  private Long id;
  private String name;
  private Long price;
}
