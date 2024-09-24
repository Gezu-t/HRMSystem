package com.hrmsystem.employeeprofileservice.dal.repository;

import com.hrmsystem.employeeprofileservice.dal.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {

}