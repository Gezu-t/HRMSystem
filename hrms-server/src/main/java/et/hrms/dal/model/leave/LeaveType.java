package et.hrms.dal.model.leave;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "leave_type")
public class LeaveType {

  @Id
  @GeneratedValue(generator = "leave_type_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "leave_type_id_gen", sequenceName = "leave_type_id_seq", allocationSize = 1)
  private Long id;
  @Column(name = "leave_type_name", nullable = false, unique = true, length = 245)
  private String name;
  private String description;
}
