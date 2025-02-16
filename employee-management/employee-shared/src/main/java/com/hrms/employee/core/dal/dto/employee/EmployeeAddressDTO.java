package com.hrms.employee.core.dal.dto.employee;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddressDTO {

    private Long id;

    @Size(max = 15)
    private String telNumberHome;

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

    @Size(max = 20)
    private String addressType;

    @Size(max = 20)
    private String addressStatus;

    private LocalDate addressStatusDate;

    @Size(max = 255)
    private String addressStatusDescription;

    private Long employeeId;

    // Getters and Setters
}