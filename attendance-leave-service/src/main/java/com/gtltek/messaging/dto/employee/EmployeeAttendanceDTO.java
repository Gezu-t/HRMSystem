package com.gtltek.messaging.dto.employee;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttendanceDTO {
    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String genderStatus;
    private String maritalStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String employeeType;
    private LocalDate dateOfLeaving;
    private LocalDate dateOfResignation;
    private Boolean employeeStatus;
    private LocalDate employeeStatusDate;
    private byte[] employeeProfileImage;

    private Long departmentId;
    private Long branchId;
    private Long organizationId;
    private String status;
}