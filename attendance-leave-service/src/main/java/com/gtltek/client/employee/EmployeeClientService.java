package com.gtltek.client.employee;

import com.gtltek.messaging.dto.employee.EmployeeAttendanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClientService {

    @GetMapping("/api/v1/employees/{id}")
    EmployeeAttendanceDTO getEmployeeById(@PathVariable("id") Long employeeId);
}