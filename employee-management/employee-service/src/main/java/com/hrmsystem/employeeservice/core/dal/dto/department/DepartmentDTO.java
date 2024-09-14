package com.hrmsystem.employeeservice.core.dal.dto.department;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank
    private String departmentNumber;

    @NotBlank
    private String departmentName;

    private String locations;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private BranchDTO branch;

    private OrganizationDTO organization;

}
