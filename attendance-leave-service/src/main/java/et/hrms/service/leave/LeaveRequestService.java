package et.hrms.service.leave;

import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import jakarta.transaction.Transactional;

public interface LeaveRequestService {
    LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId);
    LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto);
}
