package com.hrmsystem.employeeprofileservice.dal.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter@NoArgsConstructor
@AllArgsConstructor
public class DepartmentUnderOrganizationDTO {
    private Long id;

    @NotBlank
    private String departmentNumber;

    @NotBlank
    private String departmentName;

    private String locations;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private OrganizationDTO organization;
}
