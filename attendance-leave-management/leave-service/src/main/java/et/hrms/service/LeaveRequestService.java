package et.hrms.service;

import et.hrms.dal.dto.employee.EmployeeLeaveDTO;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;

import java.util.Optional;

public interface LeaveRequestService {
    LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId);
    LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto);

    Optional<EmployeeLeaveDTO> handleLeaveRequestForEmployee(Long employeeId);
}
