package et.hrms.dal.dto.leave;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.model.leave.LeaveRequestStates;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


public class LeaveRequestDTO  implements Serializable {

  @Serial
  private static final long serialVersionUID = -1431412166751946589L;


  private Long leaveRequestId;

  @NotNull(message = "Start date cannot be null")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate startDate;


  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate endDate;

  private LeaveRequestTypeDTO leaveRequestTypeDTO;
  private LeaveRequestEvents leaveRequestEvents;
  private LeaveRequestStates leaveRequestStates;

  private Long employeeId;
  private Long leaveBalanceId;
  private Long taskId;
  private Long projectId;

  private String leaveStatus;
  private Boolean status;


  private String employeeName;
  private String employeeDepartment;
  private String department;
  private String leaveRequestReason;
  private String approverComments;

  public Long getLeaveRequestId() {
    return leaveRequestId;
  }

  public void setLeaveRequestId(Long leaveRequestId) {
    this.leaveRequestId = leaveRequestId;
  }

  public @NotNull(message = "Start date cannot be null") LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(@NotNull(message = "Start date cannot be null") LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public LeaveRequestTypeDTO getLeaveRequestTypeDTO() {
    return leaveRequestTypeDTO;
  }

  public void setLeaveRequestTypeDTO(LeaveRequestTypeDTO leaveRequestTypeDTO) {
    this.leaveRequestTypeDTO = leaveRequestTypeDTO;
  }

  public LeaveRequestEvents getLeaveRequestEvents() {
    return leaveRequestEvents;
  }

  public void setLeaveRequestEvents(LeaveRequestEvents leaveRequestEvents) {
    this.leaveRequestEvents = leaveRequestEvents;
  }

  public LeaveRequestStates getLeaveRequestStates() {
    return leaveRequestStates;
  }

  public void setLeaveRequestStates(LeaveRequestStates leaveRequestStates) {
    this.leaveRequestStates = leaveRequestStates;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Long getLeaveBalanceId() {
    return leaveBalanceId;
  }

  public void setLeaveBalanceId(Long leaveBalanceId) {
    this.leaveBalanceId = leaveBalanceId;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getLeaveStatus() {
    return leaveStatus;
  }

  public void setLeaveStatus(String leaveStatus) {
    this.leaveStatus = leaveStatus;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getEmployeeDepartment() {
    return employeeDepartment;
  }

  public void setEmployeeDepartment(String employeeDepartment) {
    this.employeeDepartment = employeeDepartment;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getLeaveRequestReason() {
    return leaveRequestReason;
  }

  public void setLeaveRequestReason(String leaveRequestReason) {
    this.leaveRequestReason = leaveRequestReason;
  }

  public String getApproverComments() {
    return approverComments;
  }

  public void setApproverComments(String approverComments) {
    this.approverComments = approverComments;
  }
}
