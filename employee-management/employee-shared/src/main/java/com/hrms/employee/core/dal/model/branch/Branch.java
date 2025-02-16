package com.hrms.employee.core.dal.model.branch;


import com.hrms.employee.core.dal.model.department.Department;
import com.hrms.employee.core.dal.model.employee.Employee;
import com.hrms.employee.core.dal.model.organization.Organization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "branch")
public class Branch implements Serializable {

    @Serial
    private static final long serialVersionUID = -4608504281253978770L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branchId")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String branchCode;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String branchName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Employee> employees;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
