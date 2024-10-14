package et.hrms.dal.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

  @Serial
  private static final long serialVersionUID = 4784327991784793970L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "absentee_reported_by")
  private String absenteeReportedBy;

  @Column(name = "absentee_date")
  private LocalDate absenteeDate;

  @Column(name = "absentee_description")
  private String absenteeDescription;

  @Column(name = "recorded_by")
  private String recordedBy;

  @Column(name = "recorded_date")
  private LocalDate recordedDate;

  @Column(name = "start_time")
  private LocalDateTime startTime;

  @Column(name = "end_time")
  private LocalDateTime endTime;

  @Column(name = "attendance_state")
  private String attendanceState;

  @Column(name = "for_department")
  private String forDepartment;

  @Enumerated(EnumType.STRING)
  @Column(name = "attendance_status")
  private AttendanceStatus attendanceStatus;

  @Column(name = "employee_id")
  private Long employeeId;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  // Method to calculate working hours for this attendance entry
  public double calculateWorkingHours() {
    if (startTime != null && endTime != null) {
      return Duration.between(startTime, endTime).toMinutes() / 60.0;
    }
    return 0.0;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAbsenteeReportedBy() {
    return absenteeReportedBy;
  }

  public void setAbsenteeReportedBy(String absenteeReportedBy) {
    this.absenteeReportedBy = absenteeReportedBy;
  }

  public LocalDate getAbsenteeDate() {
    return absenteeDate;
  }

  public void setAbsenteeDate(LocalDate absenteeDate) {
    this.absenteeDate = absenteeDate;
  }

  public String getAbsenteeDescription() {
    return absenteeDescription;
  }

  public void setAbsenteeDescription(String absenteeDescription) {
    this.absenteeDescription = absenteeDescription;
  }

  public String getRecordedBy() {
    return recordedBy;
  }

  public void setRecordedBy(String recordedBy) {
    this.recordedBy = recordedBy;
  }

  public LocalDate getRecordedDate() {
    return recordedDate;
  }

  public void setRecordedDate(LocalDate recordedDate) {
    this.recordedDate = recordedDate;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public String getAttendanceState() {
    return attendanceState;
  }

  public void setAttendanceState(String attendanceState) {
    this.attendanceState = attendanceState;
  }

  public String getForDepartment() {
    return forDepartment;
  }

  public void setForDepartment(String forDepartment) {
    this.forDepartment = forDepartment;
  }

  public AttendanceStatus getAttendanceStatus() {
    return attendanceStatus;
  }

  public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
    this.attendanceStatus = attendanceStatus;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
