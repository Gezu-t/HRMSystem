package com.hrmsystem.employeeservice.core.dal.repository.employee;

import com.hrmsystem.employeeservice.core.dal.model.employee.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long>{
}