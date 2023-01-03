package et.hrms.dal.dto;

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
}
