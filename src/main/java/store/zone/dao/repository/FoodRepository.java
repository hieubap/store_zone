package store.zone.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.zone.dao.model.FoodEntity;

public interface FoodRepository extends JpaRepository<FoodEntity,Long> {

}
