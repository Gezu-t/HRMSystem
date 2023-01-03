package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
//    @SequenceGenerator(name = "address_id_gen",
//            sequenceName = "address_id_seq",
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "address_id_gen")

    private Long id;

    private String telNumberHome;
    private String telNumberOffice;
    private  String mobile;
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
    private String addressStatusDate;
    private String addressStatusDescription;

    @OneToMany(mappedBy = "address")
    private Set<OrganizationAddressManagement> organizationAddressManagements;
    @OneToMany(mappedBy = "address")
    private Set<EmployeeAddressManagement> employeeAddressManagements;


}
