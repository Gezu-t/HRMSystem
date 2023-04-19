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
@Table(name="leave_request")
public class LeaveRequest {
  @Id
  @GeneratedValue(generator = "leave_request_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "leave_request_gen", sequenceName = "leave_request_seq", allocationSize = 1)
  private Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "leaveTypeId")
  private LeaveType leaveType;
  @Enumerated(EnumType.STRING)
  private LeaveRequestEvents leaveRequestEvents;
  @Enumerated(EnumType.STRING)
  private LeaveRequestStates leaveRequestStates;
  private LocalDate requestDate;
  private String leaveRequestReason;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employeeId")
  private Employee employee;


}
