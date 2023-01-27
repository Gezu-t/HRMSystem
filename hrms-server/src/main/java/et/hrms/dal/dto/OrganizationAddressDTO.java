package et.hrms.dal.dto;


import lombok.*;

@Data
public class OrganizationAddressDTO {

    private Long addressId;
    private String telNumberHome;
    private String telNumberOffice;
    private String mobile;
    private Integer houseNumber;
    private String street;
    private Integer streetNumber;
    private String region;
    private String province;
    private String city;
    private String postalCode;
    private String country;


}
