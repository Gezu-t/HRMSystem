package com.gtltek.employee.core.dal.model.organization;

import com.gtltek.employee.core.dal.model.branch.Address;
import com.gtltek.employee.core.dal.model.branch.Branch;
import com.gtltek.employee.core.dal.model.department.Department;
import com.gtltek.employee.core.dal.model.employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    @Serial
    private static final long serialVersionUID = -5895464065664543655L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String organizationName;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String organizationCode;

    @Column(nullable = false)
    private LocalDate establishmentDate;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branch> branches;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Owners> owners = new ArrayList<>();

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
