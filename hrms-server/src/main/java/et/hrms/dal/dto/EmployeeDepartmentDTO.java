package et.hrms.dal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class EmployeeDepartmentDTO {

    private Long employeeDepartmentId;
    private Long employeeId;
    private Long departmentId;

}
