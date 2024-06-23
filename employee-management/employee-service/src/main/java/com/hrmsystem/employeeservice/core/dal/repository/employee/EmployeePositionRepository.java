package com.hrmsystem.employeeservice.core.dal.repository.employee;

import com.hrmsystem.employeeservice.core.dal.model.employee.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
}
