package com.hrmsystem.employeeservice.core.dal.model.department;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "department")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Department implements Serializable {

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

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
