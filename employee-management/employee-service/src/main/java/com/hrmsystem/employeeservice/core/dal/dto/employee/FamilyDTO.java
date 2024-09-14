package com.hrmsystem.employeeservice.core.dal.dto.employee;


import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyDTO {
    private Long id;

    @Size(max = 50)
    private String nationality;

    @Size(max = 10)
    private String payGrade;

    private LocalDate dateBirth;

    @Size(max = 10)
    private String gender;

    @Size(max = 50)
    private String familyFirstName;

    @Size(max = 50)
    private String familyLastName;

    @Size(max = 15)
    private String emergencyContact;

    private Long employeeId;

}
