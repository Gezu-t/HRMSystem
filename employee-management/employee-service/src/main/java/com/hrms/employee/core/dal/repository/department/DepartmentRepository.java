package com.hrms.employee.core.dal.repository.department;

import com.hrms.employee.core.dal.model.department.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByOrganizationIdAndBranchIsNull(Long organizationId, Sort sort);

    List<Department> findByBranchId(Long branchId, Sort sort);
}
