package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
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
    private String dateOfBirth;
    private String dateOfJoining;
    private String dateOfLeaving;
    private String dateOfResignation;
    private Boolean employeeStatus;
    private String employeeStatusDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address addresses;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Appearance appearance;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Family family;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeeDepartmentManagement> employeeDepartmentManagements;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeeEducationManagement> employeeEducationManagements;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeePositionManagement> employeePositionManagements;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeeAddressManagement> employeeAddressManagements;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EmployeeEvaluation> employeeEvaluation;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EmployeePromotion> employeePromotion;

}
