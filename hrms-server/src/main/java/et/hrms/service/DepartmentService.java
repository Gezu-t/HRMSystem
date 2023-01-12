package et.hrms.service;

import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(DepartmentDTO departmentDTO,
                                OrganizationDTO organizationDTO) throws Exception;

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws Exception;

    List<DepartmentDTO> getAllDepartment(int page, int size);
}
