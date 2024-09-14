package com.hrmsystem.employeeservice.core.dal.dto.department;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {

    private Long id;

    @NotBlank
    private String organizationName;

    private String organizationCode;

    @PastOrPresent
    private LocalDate establishmentDate;

    @NotBlank
    private String ownerName;

    private OrganizationAddressDTO organizationAddressDTO;


}
