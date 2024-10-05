package et.hrms.dal.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.attendance.AttendanceCertifiedStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AttendanceCertifiedDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = -1290723550775155440L;


  private Long attendanceCertifiedId;
  private String certifiedBy;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate certifiedDate;
  private AttendanceCertifiedStatus certifyStatus;
  private String description;

}
