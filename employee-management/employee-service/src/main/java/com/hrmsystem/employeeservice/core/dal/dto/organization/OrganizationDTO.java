package com.hrmsystem.employeeservice.core.dal.dto.organization;


import com.hrmsystem.employeeservice.core.dal.dto.branch.BranchDTO;
import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


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

    private List<BranchDTO> branches;
    private List<DepartmentDTO> departments;


}
