package et.hrms.service;

import et.hrms.dal.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size);

    List<EmployeeDTO> getAllEmployeeList(int page, int size);

    EmployeeDTO getEmployeeById(Long id);
}
