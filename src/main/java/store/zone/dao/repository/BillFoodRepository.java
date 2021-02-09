package store.zone.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.zone.dao.model.BillFoodEntity;

public interface BillFoodRepository extends JpaRepository<BillFoodEntity,Long> {
}
