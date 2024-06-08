package et.hrms.dal.dto.employee;


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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public @NotNull String getTelNumberHome() {
        return telNumberHome;
    }

    public void setTelNumberHome(@NotNull String telNumberHome) {
        this.telNumberHome = telNumberHome;
    }

    public @NotNull String getTelNumberOffice() {
        return telNumberOffice;
    }

    public void setTelNumberOffice(@NotNull String telNumberOffice) {
        this.telNumberOffice = telNumberOffice;
    }

    public @NotNull String getMobile() {
        return mobile;
    }

    public void setMobile(@NotNull String mobile) {
        this.mobile = mobile;
    }

    public @NotNull Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(@NotNull Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public @NotNull String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    public @NotNull Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(@NotNull Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public @NotNull String getRegion() {
        return region;
    }

    public void setRegion(@NotNull String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public @NotNull String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    public @NotNull String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@NotNull String postalCode) {
        this.postalCode = postalCode;
    }

    public @NotNull String getCountry() {
        return country;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }
}
