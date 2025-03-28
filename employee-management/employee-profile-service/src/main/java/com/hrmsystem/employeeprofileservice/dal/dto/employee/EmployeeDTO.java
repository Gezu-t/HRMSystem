package com.hrmsystem.employeeprofileservice.dal.dto.employee;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDTO {
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
    private String departmentName;
    private Long branchId;
    private String branchName;
    private Long organizationId;
    private String organizationName;

    private EmployeeAppearanceDTO employeeAppearance;
    private FamilyDTO family;
    private List<EmployeePositionManagementDTO> employeePositionManagements;
    private List<EmployeeEvaluationDTO> employeeEvaluations;
    private List<EmployeeAddressDTO> employeeAddresses;
    private List<EmployeeDetailDTO> employeeDetails;
    private List<EmployeePromotionDTO> employeePromotions;

}