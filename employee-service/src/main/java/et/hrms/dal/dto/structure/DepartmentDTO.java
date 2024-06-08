package et.hrms.dal.dto.structure;


import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;


public class DepartmentDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = -1847874523933760900L;

    private Long departmentId;
    @NotEmpty
    private String locations;
    @NotEmpty
    private String departmentName;

    private Long branchId;

    private Long organizationId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public @NotEmpty String getLocations() {
        return locations;
    }

    public void setLocations(@NotEmpty String locations) {
        this.locations = locations;
    }

    public @NotEmpty String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(@NotEmpty String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
