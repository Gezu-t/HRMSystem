package com.hrmsystem.employeeservice.core.dal.repository.department;

import com.hrmsystem.employeeservice.core.dal.model.department.DepartmentUnderOrganization;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentUnderOrganizationRepository extends JpaRepository<DepartmentUnderOrganization, Long> {
    List<DepartmentUnderOrganization> findByOrganizationId(Long organizationId, Sort sort);



    @Query("SELECT d FROM DepartmentUnderOrganization d WHERE d.id = :departmentId")
    DepartmentUnderOrganization findDepartmentById(@Param("departmentId") Long departmentId);
}