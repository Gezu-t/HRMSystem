package et.hrms.service;

import et.hrms.dal.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> findEmployeeByDepartmentName(String departmentName, int page, int size);

    List<EmployeeDTO> getAllEmployeeList(int page, int size);

}
