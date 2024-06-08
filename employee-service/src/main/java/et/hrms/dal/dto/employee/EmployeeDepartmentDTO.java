package et.hrms.dal.dto.employee;


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

    public Long getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public void setEmployeeDepartmentId(Long employeeDepartmentId) {
        this.employeeDepartmentId = employeeDepartmentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
