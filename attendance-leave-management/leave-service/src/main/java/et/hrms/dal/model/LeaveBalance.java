package et.hrms.dal.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "leave_balance")
public class LeaveBalance implements Serializable {
  @Serial
  private static final long serialVersionUID = -3753600462914869750L;


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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getRemainingDays() {
    return remainingDays;
  }

  public void setRemainingDays(Integer remainingDays) {
    this.remainingDays = remainingDays;
  }

  public Integer getNumberOfLeavesTaken() {
    return numberOfLeavesTaken;
  }

  public void setNumberOfLeavesTaken(Integer numberOfLeavesTaken) {
    this.numberOfLeavesTaken = numberOfLeavesTaken;
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

  public List<LeaveRequest> getLeaveRequests() {
    return leaveRequests;
  }

  public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
    this.leaveRequests = leaveRequests;
  }
}
