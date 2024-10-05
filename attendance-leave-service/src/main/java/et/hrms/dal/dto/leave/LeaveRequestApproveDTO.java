package et.hrms.dal.dto.leave;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.leave.LeaveRequestEvents;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


public class LeaveRequestApproveDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 6523409404409252561L;


  private Long leaveRequestApproveId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate requestApproveDate;
  private Long approvalUserId;
  private Long leaveRequestId;
  private LeaveRequestEvents leaveRequestEvents;
  private boolean status;
  private String approvalUserComment;
  private String description;


  public Long getLeaveRequestApproveId() {
    return leaveRequestApproveId;
  }

  public void setLeaveRequestApproveId(Long leaveRequestApproveId) {
    this.leaveRequestApproveId = leaveRequestApproveId;
  }

  public LocalDate getRequestApproveDate() {
    return requestApproveDate;
  }

  public void setRequestApproveDate(LocalDate requestApproveDate) {
    this.requestApproveDate = requestApproveDate;
  }

  public Long getApprovalUserId() {
    return approvalUserId;
  }

  public void setApprovalUserId(Long approvalUserId) {
    this.approvalUserId = approvalUserId;
  }

  public Long getLeaveRequestId() {
    return leaveRequestId;
  }

  public void setLeaveRequestId(Long leaveRequestId) {
    this.leaveRequestId = leaveRequestId;
  }

  public LeaveRequestEvents getLeaveRequestEvents() {
    return leaveRequestEvents;
  }

  public void setLeaveRequestEvents(LeaveRequestEvents leaveRequestEvents) {
    this.leaveRequestEvents = leaveRequestEvents;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
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
