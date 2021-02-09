package store.zone.dto;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
  private Long userId;
  private Map<Long,Integer> listFoodsOrder;
}
