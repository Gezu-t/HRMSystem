package com.hrmsystem.employeeservice.core.dal.dto.department;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String departmentNumber;
    private String departmentName;
    private String locations;
    private Long branchId;
    private String branchName;
    private Long organizationId;
    private String organizationName;
}