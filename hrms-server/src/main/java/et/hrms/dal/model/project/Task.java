package et.hrms.dal.model.project;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table
public class Task {
  @Id
  @GeneratedValue(generator = "task_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "task_id_gen", sequenceName = "task_id_seq", allocationSize = 1)
  private Long id;

}
