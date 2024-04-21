package et.hrms.dal.model.employee;


import et.hrms.dal.model.GenderType;
import et.hrms.dal.model.MaritalStatus;
import et.hrms.dal.model.User;
import et.hrms.dal.model.education.Education;
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


@Setter@Getter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private GenderType genderStatus;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    private LocalDate dateOfLeaving;
    private LocalDate dateOfResignation;
    private Boolean employeeStatus;
    private LocalDate employeeStatusDate;
    private Byte employeeProfileImage;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeAppearance employeeAppearance;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Family family;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeePositionManagement> employeePositionManagements = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEvaluation> employeeEvaluation;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeAddress> employeeAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeDetail> employeeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeePromotion> employeePromotion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "employee")
    private List<Education> educations;


}
