package com.hrmsystem.employeeservice.core.service.department;

import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO departmentDTO, Long branchId, Long organizationId);

    DepartmentDTO getDepartmentById(Long id);

    List<DepartmentDTO> getDepartmentsByBranch(Long branchId, Sort sort);

    List<DepartmentDTO> getDepartmentsByOrganization(Long organizationId, Sort sort);

    List<DepartmentDTO> getAllDepartments(int page, int size, Sort sort);

    void deleteDepartment(Long id);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
}
