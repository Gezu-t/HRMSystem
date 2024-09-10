package com.hrmsystem.employeeprofileservice.dal.model;

import dal.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_profiles")
@Data
public class EmployeeProfile extends BaseEntity {
    @Column(name = "employee_id", unique = true, nullable = false)
    private Long employeeId;

    private String address;
    private String phoneNumber;
    private String emergencyContact;
    private String department;
    private String position;

    @Column(name = "manager_id")
    private Long managerId;

    private String educationLevel;
    private String skills;
}
