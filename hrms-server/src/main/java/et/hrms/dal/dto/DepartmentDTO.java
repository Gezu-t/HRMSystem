package et.hrms.dal.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DepartmentDTO {


    @NotEmpty
    private Long departmentId;
    private String locations;
    private String departmentName;
    private Long organizationId;

}
