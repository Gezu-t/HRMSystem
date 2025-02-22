package com.gtltek.employee.core.dal.model.employee;


import com.gtltek.employee.core.dal.model.GenderType;
import com.gtltek.employee.core.dal.model.MaritalStatus;
import com.gtltek.employee.core.dal.model.branch.Address;
import com.gtltek.employee.core.dal.model.branch.Branch;
import com.gtltek.employee.core.dal.model.department.Department;
import com.gtltek.employee.core.dal.model.education.Education;
import com.gtltek.employee.core.dal.model.organization.Organization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "employee", indexes = {
        @Index(name = "idx_employee_number", columnList = "employeeNumber", unique = true)
})
@Getter
@Setter
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 5500783237718591345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeNumber;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType genderStatus;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Past
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeAppearance employeeAppearance;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Family family;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeePositionManagement> employeePositionManagements = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEvaluation> employeeEvaluations = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeDetail> employeeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeePromotion> employeePromotions = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    private boolean deleted = false;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public int getTenureInYears() {
        return Period.between(dateOfJoining, LocalDate.now()).getYears();
    }



}
