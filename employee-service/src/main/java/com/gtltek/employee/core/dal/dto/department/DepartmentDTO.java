package com.gtltek.employee.core.dal.dto.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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