package et.hrms.dal.model.leave;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leave_request")
public class LeaveRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leaveTypeId", nullable = false)
  private LeaveRequestType leaveType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LeaveRequestEvents leaveRequestEvents;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LeaveRequestStates leaveRequestStates;

  @Column(nullable = true)
  private LocalDate requestDate;

  @Column(length = 50)
  private String leaveStatus;

  @Column(length = 255)
  private String leaveRequestReason;

  @Column(name = "employeeId", nullable = false)
  private Long employeeId;

  @Column(nullable = false)
  private Boolean status = Boolean.TRUE;

  @Column(name = "taskId")
  private Long taskId;

  @Column(name = "projectId")
  private Long projectId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leaveBalanceId")
  private LeaveBalance leaveBalance;

  @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LeaveRequestApprove> leaveRequestApproves;
}
