package com.gtltek.employee.core.dal.model.department;

import com.gtltek.employee.core.dal.model.branch.Branch;
import com.gtltek.employee.core.dal.model.employee.Employee;
import com.gtltek.employee.core.dal.model.organization.Organization;
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
@Table(name = "department")
public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = -5953809346581924427L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String departmentNumber;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String departmentName;

    @Size(max = 255)
    private String locations;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();  // Set the created date at the time of creation
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();  // Set the updated date whenever the entity is updated
    }

}
