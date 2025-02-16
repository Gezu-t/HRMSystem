package com.hrms.employee.core.dal.repository.employee;

import com.hrms.employee.core.dal.model.employee.EmployeePositionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionManageRepository extends JpaRepository<EmployeePositionManagement, Long> {
}
