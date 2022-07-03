package et.hms.dal.model;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address", schema = "public")
public class Address {

    @Id
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    private Long id;

    private String telNumberHome;
    private String telNumberOffice;
    private  String telNumberMobile;
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private Integer streetNumber;
    private String building;
    private String floor;
    private String flat;
    private String zoneCity;
    private String region;
    private String subRegion;
    private String subCity;
    private String postalCode;
    private String country;
    private String addressType;
    private String addressStatus;
    private String addressStatusDate;
    private String addressStatusDescription;


}
