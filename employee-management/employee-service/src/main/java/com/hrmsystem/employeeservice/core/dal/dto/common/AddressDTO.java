package com.hrmsystem.employeeservice.core.dal.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressDTO {

    private Long id;

    @Size(max = 15, message = "Home telephone number cannot exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,15}$", message = "Invalid home phone number format")
    private String telNumberHome;

    @Size(max = 15, message = "Office telephone number cannot exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,15}$", message = "Invalid office phone number format")
    private String telNumberOffice;

    @NotBlank(message = "Mobile number is required")
    @Size(max = 15, message = "Mobile number cannot exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,15}$", message = "Invalid mobile phone number format")
    private String mobile;

    private Integer houseNumber;

    @Size(max = 50, message = "Street name cannot exceed 50 characters")
    private String street;

    private Integer streetNumber;

    @Size(max = 50, message = "Building name cannot exceed 50 characters")
    private String building;

    @Size(max = 10, message = "Floor cannot exceed 10 characters")
    private String floor;

    @Size(max = 10, message = "Flat cannot exceed 10 characters")
    private String flat;

    @Size(max = 50, message = "Region name cannot exceed 50 characters")
    private String region;

    @Size(max = 50, message = "Province name cannot exceed 50 characters")
    private String province;

    @Size(max = 50, message = "City name cannot exceed 50 characters")
    private String city;

    @Size(max = 10, message = "Postal code cannot exceed 10 characters")
    private String postalCode;

    @Size(max = 50, message = "Country name cannot exceed 50 characters")
    private String country;
}
