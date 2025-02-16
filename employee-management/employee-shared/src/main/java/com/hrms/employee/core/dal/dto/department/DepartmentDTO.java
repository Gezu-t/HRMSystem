package com.hrms.employee.core.dal.dto.department;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String departmentNumber;
    private String departmentName;
    private String locations;
    private Long branchId;
    private Long organizationId;

}