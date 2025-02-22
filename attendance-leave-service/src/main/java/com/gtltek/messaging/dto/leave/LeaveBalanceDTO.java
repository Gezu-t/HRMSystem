package com.gtltek.messaging.dto.leave;

import java.io.Serial;
import java.io.Serializable;


public class LeaveBalanceDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = -1156752268443901501L;


  private Long leaveBalanceId;
  private String remainingDate;
  private Integer numberOfLeaveTaken;
  private Integer annualLeaveQuota;
  private Long employeeId;
  private boolean status;

  public LeaveBalanceDTO(Long leaveBalanceId, String remainingDate, Integer numberOfLeaveTaken, Integer annualLeaveQuota, Long employeeId, boolean status) {
    this.leaveBalanceId = leaveBalanceId;
    this.remainingDate = remainingDate;
    this.numberOfLeaveTaken = numberOfLeaveTaken;
    this.annualLeaveQuota = annualLeaveQuota;
    this.employeeId = employeeId;
    this.status = status;
  }

  public Long getLeaveBalanceId() {
    return leaveBalanceId;
  }

  public void setLeaveBalanceId(Long leaveBalanceId) {
    this.leaveBalanceId = leaveBalanceId;
  }

  public String getRemainingDate() {
    return remainingDate;
  }

  public void setRemainingDate(String remainingDate) {
    this.remainingDate = remainingDate;
  }

  public Integer getNumberOfLeaveTaken() {
    return numberOfLeaveTaken;
  }

  public void setNumberOfLeaveTaken(Integer numberOfLeaveTaken) {
    this.numberOfLeaveTaken = numberOfLeaveTaken;
  }

  public Integer getAnnualLeaveQuota() {
    return annualLeaveQuota;
  }

  public void setAnnualLeaveQuota(Integer annualLeaveQuota) {
    this.annualLeaveQuota = annualLeaveQuota;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
