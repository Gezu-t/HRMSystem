package et.hrms.dal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DepartmentDTO {

    private String departmentId;
    private String locations;
    private String departmentName;
    private Long organizationId;

}
