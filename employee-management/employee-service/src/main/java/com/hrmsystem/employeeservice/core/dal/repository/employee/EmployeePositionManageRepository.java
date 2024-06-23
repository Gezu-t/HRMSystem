package com.hrmsystem.employeeservice.core.dal.repository.employee;

import com.hrmsystem.employeeservice.core.dal.model.employee.EmployeePositionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionManageRepository extends JpaRepository<EmployeePositionManagement, Long> {
}
