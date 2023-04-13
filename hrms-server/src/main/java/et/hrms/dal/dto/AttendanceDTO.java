package et.hrms.dal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
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
  private String forDepartment;
  private String certifyStatus;
  private String description;
  private Long employeeId;
  private AttendanceStatus attendanceStatus;
}
