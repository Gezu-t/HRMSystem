package et.hrms.dal.dto.leave;

import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.model.leave.LeaveRequestStates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter@NoArgsConstructor@AllArgsConstructor
public class LeaveRequestDTO {


  private Long leaveRequestId;
  private LocalDate startDate;
  private LocalDate endDate;
  private LeaveRequestTypeDTO leaveRequestTypeDTO;
  private LeaveRequestEvents leaveRequestEvents;
  private LeaveRequestStates leaveRequestStates;
  private Long employeeId;
  private Long leaveBalanceId;
  private Long taskId;
  private Long projectId;
  private String leaveStatus; //new or old


}
