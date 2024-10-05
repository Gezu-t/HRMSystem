package et.hrms.dal.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AttendanceApproveDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 7981402252791258800L;


  private Long attendanceApproveId;
  private String approvedBy;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate approvedDate;
  private String description;
}
