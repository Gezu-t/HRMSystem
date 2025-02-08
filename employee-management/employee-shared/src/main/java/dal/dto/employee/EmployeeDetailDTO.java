package dal.dto.employee;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailDTO {

    private Long id;

    private Long employeeId;

    private Long departmentId;

}
