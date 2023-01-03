package et.hrms.dal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

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
