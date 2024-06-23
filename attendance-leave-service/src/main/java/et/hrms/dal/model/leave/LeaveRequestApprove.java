package et.hrms.dal.model.leave;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "leave_request_approve")
public class LeaveRequestApprove implements Serializable {

  @Serial
  private static final long serialVersionUID = 3479418185548526724L;


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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getRequestApproveDate() {
    return requestApproveDate;
  }

  public void setRequestApproveDate(LocalDate requestApproveDate) {
    this.requestApproveDate = requestApproveDate;
  }

  public Long getApprovedByEmployeeId() {
    return approvedByEmployeeId;
  }

  public void setApprovedByEmployeeId(Long approvedByEmployeeId) {
    this.approvedByEmployeeId = approvedByEmployeeId;
  }

  public LeaveRequest getLeaveRequest() {
    return leaveRequest;
  }

  public void setLeaveRequest(LeaveRequest leaveRequest) {
    this.leaveRequest = leaveRequest;
  }

  public LeaveRequestEvents getLeaveRequestEvents() {
    return leaveRequestEvents;
  }

  public void setLeaveRequestEvents(LeaveRequestEvents leaveRequestEvents) {
    this.leaveRequestEvents = leaveRequestEvents;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getApprovalUserComment() {
    return approvalUserComment;
  }

  public void setApprovalUserComment(String approvalUserComment) {
    this.approvalUserComment = approvalUserComment;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
