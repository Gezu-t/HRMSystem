package et.hrms.dal.dto.attendance;


import com.fasterxml.jackson.annotation.JsonFormat;
import dal.dto.employee.EmployeeDTO;
import et.hrms.dal.model.AttendanceStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AttendanceDTO implements Serializable {


  @Serial
  private static final long serialVersionUID = -3485894793224113890L;

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

  private String description;
  private Long employeeId;
  private AttendanceStatus attendanceStatus;
  private String forDepartment;
  private EmployeeDTO employee;

}
