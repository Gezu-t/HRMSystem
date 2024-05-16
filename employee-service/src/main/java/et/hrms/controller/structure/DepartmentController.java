package et.hrms.controller.structure;

import et.hrms.dal.dto.structure.DepartmentDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DepartmentController {


    ResponseEntity<Void> createDepartmentByBranchId(@PathVariable Long branchId,
                                                   @RequestBody List<DepartmentDTO> departmentDTO);

    ResponseEntity<Void> createDepartmentByOrganizationId(
            @PathVariable Long organizationId,
            @RequestBody @Valid List<DepartmentDTO> departmentCreateRequests);

    List<DepartmentDTO> getAllDepartment(@RequestParam("page") int page, @RequestParam("size") int size);

    ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long departmentId,
                                                   @RequestBody DepartmentDTO departmentDTO);
    List<DepartmentDTO> getDepartmentByOrganization(@PathVariable Long organizationId, @RequestParam Sort sort);

    List<DepartmentDTO> getDepartmentByBranch(Long branchId, Sort sort);
}
