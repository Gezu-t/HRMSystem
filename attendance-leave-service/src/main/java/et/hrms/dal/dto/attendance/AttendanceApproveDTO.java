package et.hrms.dal.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter@Getter@AllArgsConstructor
public class AttendanceApproveDTO {

  private Long attendanceApproveId;
  private String approvedBy;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate approvedDate;
  private String description;
}
