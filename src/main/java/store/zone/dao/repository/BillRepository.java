package store.zone.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.zone.dao.model.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity,Long> {

}
