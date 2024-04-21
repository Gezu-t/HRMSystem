package et.hrms.dal.dto.leave;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter@AllArgsConstructor@NoArgsConstructor
public class LeaveRequestApproveDTO {

  private Long leaveRequestApproveId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate requestApproveDate;
  private Long approvalUserId;
  private Long leaveRequestId;
  private LeaveRequestEvents leaveRequestEvents;
  private boolean status;
  private String approvalUserComment;
  private String description;

}
