package com.hrms.employee.core.dal.repository.employee;


import com.hrms.employee.core.dal.model.employee.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
