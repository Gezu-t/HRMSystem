package et.hrms.service.employee;

import et.hrms.dal.dto.employee.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size);

    List<EmployeeDTO> getAllEmployeeList(int page, int size);

    EmployeeDTO getEmployeeById(Long id);
}
