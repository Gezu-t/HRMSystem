package com.hrmsystem.employeeservice.core.dal.repository.employee;

import dal.model.employee.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
}
