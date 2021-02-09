package store.zone.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.zone.enums.BillStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class BillEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BillStatus status;

  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<BillFoodEntity> listFoods;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deliveredDate;

  @PrePersist
  void prePersist() {
    createdDate = LocalDateTime.now();
    updatedDate = createdDate;
  }

  @PreUpdate
  void preUpdate() {
    updatedDate = LocalDateTime.now();
  }
}
