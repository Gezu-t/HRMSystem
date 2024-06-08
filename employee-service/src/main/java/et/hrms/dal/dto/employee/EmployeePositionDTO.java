package et.hrms.dal.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
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
public class EmployeePositionDTO {

    private Long employeePositionId;
    @NotNull @NotEmpty(message = "Job title is must not be empty")
    private String jobTitle;
    @NotNull
    @NotEmpty(message = "Job level must not be empty")
    private String jobLevel;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    public Long getEmployeePositionId() {
        return employeePositionId;
    }

    public void setEmployeePositionId(Long employeePositionId) {
        this.employeePositionId = employeePositionId;
    }

    public @NotNull @NotEmpty(message = "Job title is must not be empty") String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(@NotNull @NotEmpty(message = "Job title is must not be empty") String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public @NotNull @NotEmpty(message = "Job level must not be empty") String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(@NotNull @NotEmpty(message = "Job level must not be empty") String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
