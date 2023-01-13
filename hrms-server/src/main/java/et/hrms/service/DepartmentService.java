package et.hrms.service;

import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(DepartmentDTO departmentDTO,
                                OrganizationDTO organizationDTO);

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartment(int page, int size);
}
