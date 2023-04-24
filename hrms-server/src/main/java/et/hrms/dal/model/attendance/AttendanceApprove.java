package et.hrms.dal.model.attendance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "attendance_approve")
public class AttendanceApprove {
  @Id
  @GeneratedValue(generator = "attendance_approve_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "attendance_approve_id_gen", sequenceName = "attendance_approve_id_seq", allocationSize = 1)
  private Long id;
  private String approvedBy;
  private LocalDate approvedDate;
  private String description;
}
