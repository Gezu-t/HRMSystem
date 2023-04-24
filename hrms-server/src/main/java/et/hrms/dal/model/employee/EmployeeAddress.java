package et.hrms.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_address", schema = "public")
public class EmployeeAddress {


    @Id
    @SequenceGenerator(name = "employee_address_id_gen",
            sequenceName = "employee_address_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_address_id_gen")
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
}
