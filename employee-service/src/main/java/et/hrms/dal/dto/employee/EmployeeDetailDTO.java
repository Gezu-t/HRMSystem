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

    public Long getEmployeeDetailId() {
        return employeeDetailId;
    }

    public void setEmployeeDetailId(Long employeeDetailId) {
        this.employeeDetailId = employeeDetailId;
    }

    public @NotNull EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(@NotNull EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public @NotNull DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(@NotNull DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
