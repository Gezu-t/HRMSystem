package et.hrms.dal.model.employee;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "employee_address", schema = "public")
public class EmployeeAddress implements Serializable {


    @Serial
    private static final long serialVersionUID = 500065730038278001L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telNumberHome;
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
    private String addressType;
    private String addressStatus;
    private LocalDate addressStatusDate;
    private String addressStatusDescription;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

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

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }

    public LocalDate getAddressStatusDate() {
        return addressStatusDate;
    }

    public void setAddressStatusDate(LocalDate addressStatusDate) {
        this.addressStatusDate = addressStatusDate;
    }

    public String getAddressStatusDescription() {
        return addressStatusDescription;
    }

    public void setAddressStatusDescription(String addressStatusDescription) {
        this.addressStatusDescription = addressStatusDescription;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
