package com.hrmsystem.employeeprofileservice.dal.dto.employee;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeAppearanceDTO {

    private Long id;

    @Size(max = 5)
    private String height;

    @Size(max = 5)
    private String weight;

    @Size(max = 20)
    private String hairColor;

    @Size(max = 20)
    private String eyeColor;

    @Size(max = 20)
    private String skinColor;

    @Size(max = 3)
    private String bloodGroup;

    @Size(max = 10)
    private String chest;

    private Long employeeId;

}

