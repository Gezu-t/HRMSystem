package et.hrms.dal.dto.attendance;


import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.attendance.AttendanceStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


public class AttendanceDTO implements Serializable {


  @Serial
  private static final long serialVersionUID = -3485894793224113890L;

  private Long attendanceId;
  @NotNull(message = "attendance employeeprofile name must not be null")
  private String absenteeReportedBy;
  @NotNull(message = "attendance date must not be null")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate absenteeDate;
  private String absenteeDescription;
  private String recordedBy;

  @NotEmpty(message = "Attendance record Date is not empty!")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate recordedDate;
  private String attendanceState;
  private String approvedBy;

  @NotEmpty(message = "Attendance approve Date is not empty!")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate approvedDate;
  private String certifiedBy;
  @NotEmpty(message = "Attendance certified Date is not empty!")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate certifiedDate;

  private String description;
  private Long employeeId;
  private AttendanceStatus attendanceStatus;
  private String forDepartment;


  public Long getAttendanceId() {
    return attendanceId;
  }

  public void setAttendanceId(Long attendanceId) {
    this.attendanceId = attendanceId;
  }

  public @NotNull(message = "attendance employeeprofile name must not be null") String getAbsenteeReportedBy() {
    return absenteeReportedBy;
  }

  public void setAbsenteeReportedBy(@NotNull(message = "attendance employeeprofile name must not be null") String absenteeReportedBy) {
    this.absenteeReportedBy = absenteeReportedBy;
  }

  public @NotNull(message = "attendance date must not be null") LocalDate getAbsenteeDate() {
    return absenteeDate;
  }

  public void setAbsenteeDate(@NotNull(message = "attendance date must not be null") LocalDate absenteeDate) {
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

  public @NotEmpty(message = "Attendance record Date is not empty!") LocalDate getRecordedDate() {
    return recordedDate;
  }

  public void setRecordedDate(@NotEmpty(message = "Attendance record Date is not empty!") LocalDate recordedDate) {
    this.recordedDate = recordedDate;
  }

  public String getAttendanceState() {
    return attendanceState;
  }

  public void setAttendanceState(String attendanceState) {
    this.attendanceState = attendanceState;
  }

  public String getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

  public @NotEmpty(message = "Attendance approve Date is not empty!") LocalDate getApprovedDate() {
    return approvedDate;
  }

  public void setApprovedDate(@NotEmpty(message = "Attendance approve Date is not empty!") LocalDate approvedDate) {
    this.approvedDate = approvedDate;
  }

  public String getCertifiedBy() {
    return certifiedBy;
  }

  public void setCertifiedBy(String certifiedBy) {
    this.certifiedBy = certifiedBy;
  }

  public @NotEmpty(message = "Attendance certified Date is not empty!") LocalDate getCertifiedDate() {
    return certifiedDate;
  }

  public void setCertifiedDate(@NotEmpty(message = "Attendance certified Date is not empty!") LocalDate certifiedDate) {
    this.certifiedDate = certifiedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public AttendanceStatus getAttendanceStatus() {
    return attendanceStatus;
  }

  public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
    this.attendanceStatus = attendanceStatus;
  }

  public String getForDepartment() {
    return forDepartment;
  }

  public void setForDepartment(String forDepartment) {
    this.forDepartment = forDepartment;
  }
}
