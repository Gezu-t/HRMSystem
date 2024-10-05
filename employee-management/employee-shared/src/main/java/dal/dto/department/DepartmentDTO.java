package dal.dto.department;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String departmentNumber;
    private String departmentName;
    private String locations;
    private Long branchId;
    private Long organizationId;

}