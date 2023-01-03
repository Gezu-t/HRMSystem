package et.hrms.service;

import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartment();
}
