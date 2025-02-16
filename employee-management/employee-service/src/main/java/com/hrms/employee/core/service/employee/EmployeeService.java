package com.hrms.employee.core.service.employee;

import com.hrms.employee.core.dal.dto.employee.EmployeeDTO;
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
