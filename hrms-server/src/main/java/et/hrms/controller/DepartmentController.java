package et.hrms.controller;

import et.hrms.dal.dto.DepartmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

public interface DepartmentController {


    List<DepartmentDTO> createDepartmentByBranchId(@PathVariable Long branchId,
                                                   @RequestBody DepartmentDTO departmentDTO);

    Set<DepartmentDTO> createDepartmentByOrganizationId(@PathVariable Long organizationId,
                                                        @RequestBody DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartment(@RequestParam("page") int page, @RequestParam("size") int size);

    ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,
                                                   @RequestBody DepartmentDTO departmentDTO);
}
