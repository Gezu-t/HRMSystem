package et.hrms.dal.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String department;
    private String position;
    private String status;


}
