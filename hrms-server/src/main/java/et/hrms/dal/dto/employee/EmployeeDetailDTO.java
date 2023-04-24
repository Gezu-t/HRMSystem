package et.hrms.dal.dto.employee;

import et.hrms.dal.dto.structure.DepartmentDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class EmployeeDetailDTO {

    private Long employeeDetailId;

    @NotNull
    private EmployeeDTO employeeDTO;

    @NotNull
    private DepartmentDTO departmentDTO;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
