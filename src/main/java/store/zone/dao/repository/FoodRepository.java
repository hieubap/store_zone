package store.zone.dao.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.zone.dao.model.FoodEntity;
import store.zone.dto.FoodDTO;

public interface FoodRepository extends JpaRepository<FoodEntity,Long> {
  @Query(
      value = "select f.* from food f "
          + " where true "
          + " and ( LOWER(f.name) like concat('%',LOWER(:#{#dto.name}),'%') or LOWER(:#{#dto.name}) is null ) ",
      nativeQuery = true
  )
  List<FoodEntity> search(FoodDTO dto);
}
