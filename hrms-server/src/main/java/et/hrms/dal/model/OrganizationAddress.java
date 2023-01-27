package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization_address", schema = "public")
public class OrganizationAddress {

    @Id
    @SequenceGenerator(name = "organizationAddress_id_gen",
            sequenceName = "organizationAddress_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "organizationAddress_id_gen")
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




}
