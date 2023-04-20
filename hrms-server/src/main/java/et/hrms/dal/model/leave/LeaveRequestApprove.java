package et.hrms.dal.model.leave;

import et.hrms.dal.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "leave_request_approve")
public class LeaveRequestApprove {

  @Id
  @SequenceGenerator(name = "leave_request_approve_id_gen", sequenceName = "leave_request_approve_seq", allocationSize = 1)
  @GeneratedValue(generator = "leave_request_approve_id_gen", strategy = GenerationType.SEQUENCE)
  private Long id;
  private LocalDate requestApproveDate;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "employeeId")
  private Employee approvedBy;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leaveRequestId")
  private LeaveRequest leaveRequest;
  @Enumerated(EnumType.STRING)
  private LeaveRequestEvents leaveRequestEvents;
  private Boolean status;


}
