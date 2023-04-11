package et.hrms.dal.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
public class OrganizationAddressDTO {

    @NotNull
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
