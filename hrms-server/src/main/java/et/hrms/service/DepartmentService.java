package et.hrms.service;

import et.hrms.dal.dto.DepartmentDTO;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface DepartmentService {


    List<DepartmentDTO> createDepartmentByBranchId(long branchId, DepartmentDTO departmentDTO);

    Set<DepartmentDTO> createDepartmentByOrganizationId(Long organizationId, DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getDepartmentByOrganization(Long organizationId, Sort sort);

    List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort);

    List<DepartmentDTO> getAllDepartment(int page, int size);


}
