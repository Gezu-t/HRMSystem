package et.hrms.dal.dto.employee;

import java.io.Serial;
import java.io.Serializable;

public class EmployeeDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = -2879116760040809204L;


    private Long id;
    private String name;
    private String department;
    private String position;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
