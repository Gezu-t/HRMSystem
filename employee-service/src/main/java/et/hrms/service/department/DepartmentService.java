package et.hrms.service.department;

import et.hrms.dal.dto.department.DepartmentUnderBranchDTO;
import et.hrms.dal.dto.department.DepartmentUnderOrganizationDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DepartmentService {
    void createDepartmentByBranchId(long branchId, List<DepartmentUnderBranchDTO> departmentUnderBranchDTOS);
    void createDepartmentByOrganizationId(Long organizationId, List<DepartmentUnderOrganizationDTO> underOrganizationDTOS);
    DepartmentUnderBranchDTO getDepartmentUnderBranchById(Long id);
    DepartmentUnderOrganizationDTO getDepartmentUnderOrganizationById(Long id);
    DepartmentUnderBranchDTO updateDepartmentUnderBranch(Long departmentId, DepartmentUnderBranchDTO departmentDTO);
    DepartmentUnderOrganizationDTO updateDepartmentUnderOrganization(Long departmentId, DepartmentUnderOrganizationDTO departmentDTO);
    List<DepartmentUnderBranchDTO> getDepartmentByBranch(Long branchId, Sort sort);
    List<DepartmentUnderOrganizationDTO> getDepartmentByOrganization(Long organizationId, Sort sort);
    List<DepartmentUnderBranchDTO> getAllDepartmentsUnderBranch(int page, int size, Sort sort);
    List<DepartmentUnderOrganizationDTO> getAllDepartmentsUnderOrganization(int page, int size, Sort sort);
    void deleteDepartmentUnderBranch(Long id);
    void deleteDepartmentUnderOrganization(Long id);
}
