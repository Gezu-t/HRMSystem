package com.hrms.employee.core.dal.dto.employee;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EmployeeEvaluationDTO {

    private Long id;

    private LocalDate evaluationDate;

    @Size(max = 255)
    private String comment;

    @Size(max = 100)
    private String result;

    private Integer primaryVote;

    private Integer secondaryVote;

    private Boolean evaluationStatus;

    private Long employeeId;

}
