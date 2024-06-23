package com.hrmsystem.employeeprofileservice.dal.dto.employee;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePromotionDTO {

    private Long id;

    @Size(max = 10)
    private String dateOfLastPromotion;

    @Size(max = 10)
    private String dateOfLastIncrement;

    @Size(max = 10)
    private String dateOfLastDecrement;

    @Size(max = 10)
    private String dateOfLastSalaryChange;

    @Size(max = 10)
    private String dateOfLastSalaryIncrement;

    @Size(max = 10)
    private String dateOfLastSalaryDecrement;

    @Size(max = 255)
    private String dateOfLastSalaryChangeReason;

    private Boolean promotionStatus;

    private Long employeeId;

}