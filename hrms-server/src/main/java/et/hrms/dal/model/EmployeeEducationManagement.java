package et.hrms.dal.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "employee_education_management")
public class EmployeeEducationManagement {

    @Id
    @SequenceGenerator(name = "employee_education_management_gen", sequenceName = "employee_education_management_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_education_management_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;


    private LocalDateTime createdAt;

    private LocalDateTime updateAt;




}
