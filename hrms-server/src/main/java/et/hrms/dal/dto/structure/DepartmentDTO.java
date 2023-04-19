package et.hrms.dal.dto.structure;


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
    private Long departmentId;
    @NotEmpty
    private String locations;
    @NotEmpty
    private String departmentName;

    private Long branchId;

    private Long organizationId;


}
