package com.hrms.employee.core.dal.repository.employee;

import com.hrms.employee.core.dal.model.employee.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long>{
}