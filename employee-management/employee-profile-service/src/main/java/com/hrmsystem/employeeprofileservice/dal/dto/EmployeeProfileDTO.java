package com.hrmsystem.employeeprofileservice.dal.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeProfileDTO {
    private Long employeeId;
    private String address;
    private String phoneNumber;
    private String emergencyContact;
    private String department;
    private String position;
    private Long managerId;
    private String educationLevel;
    private String skills;
}
