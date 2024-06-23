package et.hrms.dal.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.attendance.AttendanceCertifiedStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


public class AttendanceCertifiedDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = -1290723550775155440L;


  private Long attendanceCertifiedId;
  private String certifiedBy;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate certifiedDate;
  private AttendanceCertifiedStatus certifyStatus;
  private String description;

  public Long getAttendanceCertifiedId() {
    return attendanceCertifiedId;
  }

  public void setAttendanceCertifiedId(Long attendanceCertifiedId) {
    this.attendanceCertifiedId = attendanceCertifiedId;
  }

  public String getCertifiedBy() {
    return certifiedBy;
  }

  public void setCertifiedBy(String certifiedBy) {
    this.certifiedBy = certifiedBy;
  }

  public LocalDate getCertifiedDate() {
    return certifiedDate;
  }

  public void setCertifiedDate(LocalDate certifiedDate) {
    this.certifiedDate = certifiedDate;
  }

  public AttendanceCertifiedStatus getCertifyStatus() {
    return certifyStatus;
  }

  public void setCertifyStatus(AttendanceCertifiedStatus certifyStatus) {
    this.certifyStatus = certifyStatus;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
