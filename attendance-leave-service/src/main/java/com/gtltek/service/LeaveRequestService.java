package com.gtltek.service;

import et.hrms.messaging.dto.employee.EmployeeLeaveDTO;
import com.gtltek.messaging.dto.leave.CreateLeaveRequestDTO;
import com.gtltek.messaging.dto.leave.LeaveRequestDTO;

import java.util.Optional;

public interface LeaveRequestService {
    LeaveRequestDTO getLeaveRequestWithEmployee(Long leaveRequestId);
    LeaveRequestDTO createLeaveRequest(CreateLeaveRequestDTO dto);

    Optional<EmployeeLeaveDTO> handleLeaveRequestForEmployee(Long employeeId);
}
