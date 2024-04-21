package et.hrms.dal.model.leave;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leave_balance")
public class LeaveBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "remaining_days")
  private Integer remainingDays;

  @Column(name = "number_of_leaves_taken")
  private Integer numberOfLeavesTaken;

  @Column(name = "annual_leave_quota")
  private Integer annualLeaveQuota;

  @Column(name = "employee_id")
  private Long employeeId;

  @OneToMany(mappedBy = "leaveBalance", fetch = FetchType.LAZY)
  private List<LeaveRequest> leaveRequests;
}
