package store.zone.dao.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.zone.dao.model.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity,Long> {
  @Query(value = "select"
      + "       year(b.created_date) as year, "
      + "       month(b.created_date) as month, "
      + "       day(b.created_date) as day, "
      + "       count(day(b.created_date)) as soluong "
      + "from bill b "
      + "where "
      + "date(b.created_date) <= :#{#end} "
      + "and date(b.created_date) >= :#{#start} "
      + "group by year(b.created_date),month(b.created_date), day(b.created_date);", nativeQuery = true)
  public List<Map<Long,Long>> dashboard(LocalDate start,LocalDate end);
}
