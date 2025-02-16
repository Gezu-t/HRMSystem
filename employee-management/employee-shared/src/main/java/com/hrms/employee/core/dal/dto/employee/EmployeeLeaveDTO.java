package com.hrms.employee.core.dal.dto.employee;

import com.hrms.employee.core.dal.dto.common.AddressDTO;
import com.hrms.employee.core.dal.dto.education.EducationDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeaveDTO {
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

    private EmployeeAppearanceDTO employeeAppearance;
    private FamilyDTO family;
    private List<EmployeePositionManagementDTO> employeePositionManagements;
    private List<EmployeeEvaluationDTO> employeeEvaluations;
    private List<AddressDTO> addresses = new ArrayList<>();
    private List<EmployeeDetailDTO> employeeDetails;
    private List<EmployeePromotionDTO> employeePromotions;
    private List<EducationDTO> educations;
    private String status;
}