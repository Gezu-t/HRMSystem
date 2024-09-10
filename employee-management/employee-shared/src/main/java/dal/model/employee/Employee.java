package dal.model.employee;


import dal.model.GenderType;
import dal.model.MaritalStatus;
import dal.model.education.Education;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 5500783237718591345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @Lob
    private byte[] employeeProfileImage;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeAppearance employeeAppearance;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Family family;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeePositionManagement> employeePositionManagements = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEvaluation> employeeEvaluations = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeAddress> employeeAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<EmployeeDetail> employeeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeePromotion> employeePromotions = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Education> educations = new ArrayList<>();


}
