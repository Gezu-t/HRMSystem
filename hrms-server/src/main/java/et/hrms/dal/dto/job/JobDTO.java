package et.hrms.dal.dto.job;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    private Long jobId;
    @NotEmpty(message = "Job title must not be null")
    private String title;
    private Integer maxSalary;

    private Integer minSalary;

    private String description;
}
