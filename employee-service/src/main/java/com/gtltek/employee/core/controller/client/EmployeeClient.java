package com.gtltek.employee.core.controller.client;


import com.gtltek.employee.core.dal.dto.employee.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employeeprofile-service")
public interface EmployeeClient {
    @GetMapping("/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id);
}