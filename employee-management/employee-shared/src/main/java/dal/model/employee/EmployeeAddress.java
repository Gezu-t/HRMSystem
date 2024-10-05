package dal.model.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "employee_address", schema = "public")
public class EmployeeAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = 500065730038278001L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String telNumberHome;

    @Column(length = 15, nullable = false)
    private String mobile;

    @Column(nullable = true)
    private Integer houseNumber;

    @Column(length = 50)
    private String street;

    @Column(nullable = true)
    private Integer streetNumber;

    @Column(length = 50)
    private String building;

    @Column(length = 10)
    private String floor;

    @Column(length = 10)
    private String flat;

    @Column(length = 50)
    private String region;

    @Column(length = 50)
    private String province;

    @Column(length = 50)
    private String city;

    @Column(length = 10)
    private String postalCode;

    @Column(length = 50)
    private String country;

    @Column(length = 20)
    private String addressType;

    @Column(length = 20)
    private String addressStatus;

    private LocalDate addressStatusDate;

    @Column(length = 255)
    private String addressStatusDescription;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Getters and Setters
    // Add here if needed


}
