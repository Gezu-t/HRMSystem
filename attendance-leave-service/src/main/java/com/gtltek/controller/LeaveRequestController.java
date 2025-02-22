package com.gtltek.controller;

import et.hrms.messaging.dto.employee.EmployeeLeaveDTO;
import com.gtltek.messaging.dto.leave.CreateLeaveRequestDTO;
import com.gtltek.messaging.dto.leave.LeaveRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface LeaveRequestController {

    ResponseEntity<LeaveRequestDTO> createLeaveRequest(@RequestBody CreateLeaveRequestDTO createDto);

    ResponseEntity<EmployeeLeaveDTO> getEmployeeDetails(@PathVariable Long employeeId);
}
