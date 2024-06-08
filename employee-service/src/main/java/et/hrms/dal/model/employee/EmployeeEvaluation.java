package et.hrms.dal.model.employee;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "employee_evaluation")
public class EmployeeEvaluation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1545759592013189443L;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getPrimaryVote() {
        return primaryVote;
    }

    public void setPrimaryVote(Integer primaryVote) {
        this.primaryVote = primaryVote;
    }

    public Integer getSecondaryVote() {
        return secondaryVote;
    }

    public void setSecondaryVote(Integer secondaryVote) {
        this.secondaryVote = secondaryVote;
    }

    public Boolean getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(Boolean evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
