package et.hrms.dal.model.employee;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_evaluation")
public class EmployeeEvaluation {

    @Id
    @SequenceGenerator(name = "employeeEvaluation_id_gen",
            sequenceName = "employeeEvaluation_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employeeEvaluation_id_gen")
    private Long id;

    private LocalDate evaluationDate;
    private String comment;
    private String result;
    private Integer primaryVote;
    private Integer secondaryVote;

    private Boolean evaluationStatus;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;




}
