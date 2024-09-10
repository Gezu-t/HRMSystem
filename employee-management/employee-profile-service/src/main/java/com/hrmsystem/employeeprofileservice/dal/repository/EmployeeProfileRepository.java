package com.hrmsystem.employeeprofileservice.dal.repository;

import com.hrmsystem.employeeprofileservice.dal.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findByEmployeeId(Long employeeId);
    List<EmployeeProfile> findByDepartment(String department);
    List<EmployeeProfile> findByManagerId(Long managerId);
}