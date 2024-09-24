package com.hrmsystem.employeeservice.core.dal.dto.organization;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrganizationAddressDTO {

    private Long id;

    @Size(max = 15)
    private String telNumberHome;

    @Size(max = 15)
    private String telNumberOffice;

    @NotBlank
    @Size(max = 15)
    private String mobile;

    private Integer houseNumber;

    @Size(max = 50)
    private String street;

    private Integer streetNumber;

    @Size(max = 50)
    private String building;

    @Size(max = 10)
    private String floor;

    @Size(max = 10)
    private String flat;

    @Size(max = 50)
    private String region;

    @Size(max = 50)
    private String province;

    @Size(max = 50)
    private String city;

    @Size(max = 10)
    private String postalCode;

    @Size(max = 50)
    private String country;

    // Getters and Setters
}
