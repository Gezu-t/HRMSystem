package et.hrms.dal.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class EmployeeAddressDTO {

    private Long addressId;
    @NotNull
    private String telNumberHome;
    @NotNull
    private String telNumberOffice;
    @NotNull
    private String mobile;
    @NotNull
    private Integer houseNumber;
    @NotNull
    private String street;
    @NotNull
    private Integer streetNumber;
    @NotNull
    private String region;
    private String province;
    @NotNull
    private String city;
    @NotNull
    private String postalCode;
    @NotNull
    private String country;
}
