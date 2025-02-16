package et.hrms.controller;

import et.hrms.dal.dto.employee.EmployeeLeaveDTO;
import et.hrms.dal.dto.leave.CreateLeaveRequestDTO;
import et.hrms.dal.dto.leave.LeaveRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface LeaveRequestController {

    ResponseEntity<LeaveRequestDTO> createLeaveRequest(@RequestBody CreateLeaveRequestDTO createDto);

    ResponseEntity<EmployeeLeaveDTO> getEmployeeDetails(@PathVariable Long employeeId);
}
