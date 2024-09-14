package com.hrmsystem.employeeservice.core.controller.employee;

import com.hrmsystem.employeeservice.core.dal.dto.employee.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeController {

    ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO);

    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id);

    ResponseEntity<List<EmployeeDTO>> getAllEmployeeList(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size);
    ResponseEntity<List<EmployeeDTO>> findEmployeeByDepartmentName(@RequestParam String departmentName,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size);
    ResponseEntity<List<EmployeeDTO>> searchEmployees(@RequestParam String name);
}
