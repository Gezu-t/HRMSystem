package com.hrmsystem.employeeservice.core.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_appearance")
public class EmployeeAppearance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String height;

    @Column(length = 5)
    private String weight;

    @Column(length = 20)
    private String hairColor;

    @Column(length = 20)
    private String eyeColor;

    @Column(length = 20)
    private String skinColor;

    @Column(length = 3)
    private String bloodGroup;

    @Column(length = 10)
    private String chest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


}
