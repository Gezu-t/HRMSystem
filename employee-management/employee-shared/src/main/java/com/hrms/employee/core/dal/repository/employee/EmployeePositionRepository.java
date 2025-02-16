package com.hrms.employee.core.dal.repository.employee;

import com.hrms.employee.core.dal.model.employee.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
}
