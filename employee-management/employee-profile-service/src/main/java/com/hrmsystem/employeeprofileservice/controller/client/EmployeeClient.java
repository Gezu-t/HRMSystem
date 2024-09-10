package com.hrmsystem.employeeprofileservice.controller.client;


import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {
    @GetMapping("/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id);
}