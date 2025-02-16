package com.hrms.employee.core.dal.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailDTO {

    private Long id;

    private Long employeeId;

    private Long departmentId;

}
