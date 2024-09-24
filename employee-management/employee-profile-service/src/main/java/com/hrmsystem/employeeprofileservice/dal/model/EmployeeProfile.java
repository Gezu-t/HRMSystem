package com.hrmsystem.employeeprofileservice.dal.model;

import dal.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "employee_profiles")
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
