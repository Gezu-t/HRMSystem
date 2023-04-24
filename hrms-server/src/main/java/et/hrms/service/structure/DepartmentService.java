package et.hrms.service.structure;

import et.hrms.dal.dto.structure.DepartmentDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DepartmentService {


    void createDepartmentByBranchId(long branchId, List<DepartmentDTO> departmentDTO);


    void createDepartmentByOrganizationId(Long organizationId, List<DepartmentDTO> departmentDTOs);

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);

    List<DepartmentDTO> getDepartmentByOrganization(Long organizationId, Sort sort);

    List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort);

    List<DepartmentDTO> getAllDepartment(int page, int size);


}
