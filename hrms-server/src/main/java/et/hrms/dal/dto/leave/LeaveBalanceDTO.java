package et.hrms.dal.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveBalanceDTO {

  private Long leaveBalanceId;
  private String remainingDate;
  private Integer numberOfLeaveTaken;
  private Integer annualLeaveQuota;
  private Long employeeId;
  private boolean status;

}
