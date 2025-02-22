package com.gtltek.messaging.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "leave_request")
public class LeaveRequest implements Serializable {
  @Serial
  private static final long serialVersionUID = 4497604150291470430L;


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


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public LeaveRequestType getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(LeaveRequestType leaveType) {
    this.leaveType = leaveType;
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

  public LocalDate getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(LocalDate requestDate) {
    this.requestDate = requestDate;
  }

  public String getLeaveStatus() {
    return leaveStatus;
  }

  public void setLeaveStatus(String leaveStatus) {
    this.leaveStatus = leaveStatus;
  }

  public String getLeaveRequestReason() {
    return leaveRequestReason;
  }

  public void setLeaveRequestReason(String leaveRequestReason) {
    this.leaveRequestReason = leaveRequestReason;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
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

  public LeaveBalance getLeaveBalance() {
    return leaveBalance;
  }

  public void setLeaveBalance(LeaveBalance leaveBalance) {
    this.leaveBalance = leaveBalance;
  }

  public List<LeaveRequestApprove> getLeaveRequestApproves() {
    return leaveRequestApproves;
  }

  public void setLeaveRequestApproves(List<LeaveRequestApprove> leaveRequestApproves) {
    this.leaveRequestApproves = leaveRequestApproves;
  }
}
