package et.hrms.dal.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class LeaveRequestTypeDTO {

  private Long leaveTypeId;
  private String leaveRequestTypeName;
}
