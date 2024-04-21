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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
