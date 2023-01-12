package et.hrms.dal.dto;


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
    private String title;

    private Integer maxSalary;

    private Integer minSalary;
    private String description;
}
