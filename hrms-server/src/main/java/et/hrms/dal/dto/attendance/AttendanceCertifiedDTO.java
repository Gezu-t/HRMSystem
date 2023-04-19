package et.hrms.dal.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.attendance.AttendanceCertifiedStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AttendanceCertifiedDTO {

  private Long attendanceCertifiedId;
  private String certifiedBy;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate certifiedDate;
  private AttendanceCertifiedStatus certifyStatus;
  private String description;
}
