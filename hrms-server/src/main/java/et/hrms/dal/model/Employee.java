package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee", schema = "public")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_id_gen",
            sequenceName = "employee_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_id_gen")
    private Long id;

    private String employeeNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private LocalDate dateOfLeaving;
    private LocalDate dateOfResignation;
    private Boolean employeeStatus;
    private LocalDate employeeStatusDate;


    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeAppearance employeeAppearance;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Family family;




//    @ManyToMany
//    @JoinTable(name = "employee_education",
//    joinColumns = {@JoinColumn(name = "employee_id")},
//    inverseJoinColumns = {@JoinColumn(name = "education_id")})
//    @SortNatural
//    private SortedSet<Education> educations = new TreeSet<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeePositionManagement> employeePositionManagements = new LinkedHashSet<>();


//    @ManyToMany
//    @JoinTable(name = "employee_address",
//    joinColumns = {@JoinColumn(name = "employee_id")},
//    inverseJoinColumns = {@JoinColumn(name = "address_id")})
//    private Set<OrganizationAddress> addresses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEvaluation> employeeEvaluation;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeAddress> employeeAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeDetail> employeeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeePromotion> employeePromotion;

}
