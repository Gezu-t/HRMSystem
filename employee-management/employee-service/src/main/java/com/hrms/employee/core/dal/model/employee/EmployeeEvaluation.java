package com.hrms.employee.core.dal.model.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "employee_evaluation")
public class EmployeeEvaluation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1545759592013189443L;

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate evaluationDate;

    @Column(length = 255)
    private String comment;

    @Column(length = 100)
    private String result;

    private Integer primaryVote;

    private Integer secondaryVote;

    private Boolean evaluationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
