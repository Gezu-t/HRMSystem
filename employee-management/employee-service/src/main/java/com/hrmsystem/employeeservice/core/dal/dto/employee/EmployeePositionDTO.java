package com.hrmsystem.employeeservice.core.dal.dto.employee;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
public class EmployeePositionDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String jobTitle;

    @Size(max = 50)
    private String jobLevel;

    private LocalDateTime startDate;

    private Set<EmployeePositionManagementDTO> employeePositionManagements;

    // Getters and Setters
}
