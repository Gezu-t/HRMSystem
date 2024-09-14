package com.hrmsystem.employeeservice.core.dal.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class EmployeeDepartmentDTO {

    private Long employeeDepartmentId;
    private Long employeeId;
    private Long departmentId;

}
