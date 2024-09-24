package com.hrmsystem.employeeprofileservice.controller.client;


import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeServiceClient {

    @GetMapping("/api/employees/{employeeId}")
    EmployeeDTO getEmployeeById(@PathVariable Long employeeId);
}