package et.hrms.dal.dto.structure;


import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;


public class OrganizationAddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4298914458989986204L;


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

    public @NotNull Long getAddressId() {
        return addressId;
    }

    public void setAddressId(@NotNull Long addressId) {
        this.addressId = addressId;
    }

    public String getTelNumberHome() {
        return telNumberHome;
    }

    public void setTelNumberHome(String telNumberHome) {
        this.telNumberHome = telNumberHome;
    }

    public String getTelNumberOffice() {
        return telNumberOffice;
    }

    public void setTelNumberOffice(String telNumberOffice) {
        this.telNumberOffice = telNumberOffice;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
