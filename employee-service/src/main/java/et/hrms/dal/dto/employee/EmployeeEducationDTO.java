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


}
