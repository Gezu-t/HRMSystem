package et.hrms.dal.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvaluationDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate evaluationDate;
    @NotNull
    private String comment;
    @NotNull
    private String result;
    @NotNull
    private Integer primaryVote;
    @NotNull
    private Integer secondaryVote;

    private Boolean evaluationStatus;

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public @NotNull String getComment() {
        return comment;
    }

    public void setComment(@NotNull String comment) {
        this.comment = comment;
    }

    public @NotNull String getResult() {
        return result;
    }

    public void setResult(@NotNull String result) {
        this.result = result;
    }

    public @NotNull Integer getPrimaryVote() {
        return primaryVote;
    }

    public void setPrimaryVote(@NotNull Integer primaryVote) {
        this.primaryVote = primaryVote;
    }

    public @NotNull Integer getSecondaryVote() {
        return secondaryVote;
    }

    public void setSecondaryVote(@NotNull Integer secondaryVote) {
        this.secondaryVote = secondaryVote;
    }

    public Boolean getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(Boolean evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }
}
