package et.hrms.dal.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {


  private Long attendanceId;
  @NotNull(message = "attendance employee name must not be null")
  private String absenteeReportedBy;
  @NotNull(message = "attendance date must not be null")
  private LocalDate absenteeDate;
  private String absenteeDescription;
  private String recordedBy;

  private LocalDate recordedDate;
  private String attendanceState;
  private String approvedBy;
  private LocalDate approvedDate;
  private String certifiedBy;
  private LocalDate certifiedDate;
  private String forDepartment;
  private String certifyStatus;
  private String description;
  private Long employeeId;
  private AttendanceStatus attendanceStatus;
}
