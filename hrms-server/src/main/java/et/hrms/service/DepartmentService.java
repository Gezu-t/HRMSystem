package et.hrms.service;

import et.hrms.dal.dto.DepartmentDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DepartmentService {


    List<DepartmentDTO> createDepartmentByBranchId(long branchId, List<DepartmentDTO> departmentDTO);


    List<DepartmentDTO> createDepartmentByOrganizationId(Long organizationId, List<DepartmentDTO> departmentDTOs);

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getDepartmentByOrganization(Long organizationId, Sort sort);

    List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort);

    List<DepartmentDTO> getAllDepartment(int page, int size);


}
