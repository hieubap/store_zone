package store.zone.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO {
  private Long id;

  private List<Bill_Food> foods;
  private UserDTO user;

  private Long total;
  private String status;

  private LocalDateTime date;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Bill_Food{
    public FoodDTO food;
    public Integer number;
    public Long price;
  }
}
