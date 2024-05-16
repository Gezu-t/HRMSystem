package et.hrms.dal.model.leave;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate requestApproveDate;

  @Column(name = "approved_by_employee_id")
  private Long approvedByEmployeeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leaveRequestId")
  private LeaveRequest leaveRequest;

  @Enumerated(EnumType.STRING)
  private LeaveRequestEvents leaveRequestEvents;

  private Boolean status;

  private String approvalUserComment;

  private String description;


}
