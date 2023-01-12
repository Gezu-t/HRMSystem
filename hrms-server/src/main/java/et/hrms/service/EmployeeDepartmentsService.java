package et.hrms.service;

import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.EmployeeDTO;
import et.hrms.dal.model.EmployeeDepartmentManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface EmployeeDepartmentsService {
    @Transactional
    void saveEmployeeWithDepartments(EmployeeDTO employeeDTO, List<DepartmentDTO> departmentDTOS);

    @Transactional
    void updateEmployeeWithDepartments(EmployeeDTO employeeDTO, List<DepartmentDTO> departmentDTOS);

    Set<EmployeeDepartmentManagement> getEmployeeDepartmentByEmployeeId(Long employeeId);
}
