package et.hrms.dal.model.employee;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "employee_position")
public class EmployeePosition implements Serializable {

    @Serial
    private static final long serialVersionUID = 9120415745622636175L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String jobLevel;

    private LocalDateTime startDate;

    @OneToMany(mappedBy = "employeePosition", fetch = FetchType.LAZY)
    private Set<EmployeePositionManagement> employeePositionManagements;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Set<EmployeePositionManagement> getEmployeePositionManagements() {
        return employeePositionManagements;
    }

    public void setEmployeePositionManagements(Set<EmployeePositionManagement> employeePositionManagements) {
        this.employeePositionManagements = employeePositionManagements;
    }
}
