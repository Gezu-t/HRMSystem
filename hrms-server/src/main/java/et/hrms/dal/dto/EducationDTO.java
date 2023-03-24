package et.hrms.dal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {

    private Long educationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate educationCompletionDate;
    @NotNull
    private String educationLevel;
    @NotNull
    private String institution;
    private String degree;
    private String educationMajor;
    private String educationMinor;
    private String educationGrade;
    private String educationType;
    private String educationStatus;
    private String award;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate awardDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate educationStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate educationEndDate;
    private String awardDescription;




}
