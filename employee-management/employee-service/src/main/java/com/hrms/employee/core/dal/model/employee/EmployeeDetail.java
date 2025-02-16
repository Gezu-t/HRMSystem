package com.hrms.employee.core.dal.model.employee;

import com.hrms.employee.core.dal.model.department.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "employee_detail", schema = "public")
public class EmployeeDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 9105210014045165938L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Getters and Setters
    // Add here if needed


}
