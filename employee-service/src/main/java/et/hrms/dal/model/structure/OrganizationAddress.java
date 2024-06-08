package et.hrms.dal.model.structure;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;



@Entity
@Table(name = "organization_address", schema = "public")
public class OrganizationAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = 7031456941954922976L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telNumberHome;
    private String telNumberOffice;
    private String mobile;
    private Integer houseNumber;
    private String street;
    private Integer streetNumber;
    private String building;
    private String floor;
    private String flat;
    private String region;
    private String province;
    private String city;
    private String postalCode;
    private String country;

    @OneToOne(mappedBy = "organizationAddress")
    private Organization organization;


    @OneToOne(mappedBy = "organizationAddress")
    private Branch branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
