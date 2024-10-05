package et.hrms.service.leave;

import dal.dto.employee.EmployeeDTO;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface LeaveRequestService {
    LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId);
    LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto);

    Optional<EmployeeDTO> handleLeaveRequestForEmployee(String employeeName);
}
