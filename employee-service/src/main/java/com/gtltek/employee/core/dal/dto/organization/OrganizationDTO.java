package com.gtltek.employee.core.dal.dto.organization;


import com.gtltek.employee.core.dal.dto.common.AddressDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {

    private Long id;
    @NotBlank
    private String organizationName;
    @NotBlank
    private String organizationCode;
    @PastOrPresent
    private LocalDate establishmentDate;
    private List<OwnersDTO> owners = new ArrayList<>();
    private List<AddressDTO> addresses = new ArrayList<>();

}
