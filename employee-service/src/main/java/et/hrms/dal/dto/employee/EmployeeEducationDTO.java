package et.hrms.dal.dto.employee;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEducationDTO {

    private Long employeeEducationId;
    @NotNull
    private Long employeeId;
    @NotNull
    private Long educationId;

    public Long getEmployeeEducationId() {
        return employeeEducationId;
    }

    public void setEmployeeEducationId(Long employeeEducationId) {
        this.employeeEducationId = employeeEducationId;
    }

    public @NotNull Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NotNull Long employeeId) {
        this.employeeId = employeeId;
    }

    public @NotNull Long getEducationId() {
        return educationId;
    }

    public void setEducationId(@NotNull Long educationId) {
        this.educationId = educationId;
    }
}
