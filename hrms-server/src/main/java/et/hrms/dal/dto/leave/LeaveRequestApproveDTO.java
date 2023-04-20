package et.hrms.dal.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@AllArgsConstructor@NoArgsConstructor
public class LeaveRequestApproveDTO {


  private Long leaveRequestApproveId;
  private Long employeeId;
  private Long leaveRequestId;

}
