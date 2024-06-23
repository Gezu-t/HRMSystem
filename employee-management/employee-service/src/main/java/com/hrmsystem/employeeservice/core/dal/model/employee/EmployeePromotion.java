package com.hrmsystem.employeeservice.core.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_promotion")
public class EmployeePromotion implements Serializable {

    @Serial
    private static final long serialVersionUID = -7141502596008484282L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String dateOfLastPromotion;

    @Column(length = 10)
    private String dateOfLastIncrement;

    @Column(length = 10)
    private String dateOfLastDecrement;

    @Column(length = 10)
    private String dateOfLastSalaryChange;

    @Column(length = 10)
    private String dateOfLastSalaryIncrement;

    @Column(length = 10)
    private String dateOfLastSalaryDecrement;

    @Column(length = 255)
    private String dateOfLastSalaryChangeReason;

    private Boolean promotionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
