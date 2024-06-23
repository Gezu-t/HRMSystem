package com.hrmsystem.employeeprofileservice.service.employee;

import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size);

    List<EmployeeDTO> getAllEmployeeList(int page, int size);

    EmployeeDTO getEmployeeById(Long id);

    @Transactional(readOnly = true)
    List<EmployeeDTO> searchEmployeesByName(String name);
}
