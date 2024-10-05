package dal.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEducationDTO {

    private Long id;

    private Long employeeId;

    private Long educationId;
}
