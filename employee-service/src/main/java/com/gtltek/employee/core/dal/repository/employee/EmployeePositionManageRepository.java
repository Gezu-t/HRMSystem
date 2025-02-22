package com.gtltek.employee.core.dal.repository.employee;

import com.gtltek.employee.core.dal.model.employee.EmployeePositionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionManageRepository extends JpaRepository<EmployeePositionManagement, Long> {
}
