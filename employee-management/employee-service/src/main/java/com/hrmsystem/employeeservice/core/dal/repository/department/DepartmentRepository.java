package com.hrmsystem.employeeservice.core.dal.repository.department;

import com.hrmsystem.employeeservice.core.dal.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
