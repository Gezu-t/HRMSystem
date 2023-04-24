package et.hrms.dal.model.leave;


import et.hrms.dal.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "leave_balance")
public class LeaveBalance {
  @Id
  @GeneratedValue(generator = "leave_balance_id_gen", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "leave_balance_id_gen", sequenceName = "leave_balance_id_seq", allocationSize = 1)
  private Long id;
  private String remainingDate;
  private Integer numberOfLeaveTaken;
  private Integer annualLeaveQuota;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employeeId")
  private Employee employee;
  @OneToMany(mappedBy = "leaveBalance")
  private List<LeaveRequest> leaveRequests;

}
