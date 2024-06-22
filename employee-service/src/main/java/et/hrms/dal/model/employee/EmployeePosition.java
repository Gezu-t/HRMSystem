package et.hrms.dal.model.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "employee_position")
public class EmployeePosition implements Serializable {

    @Serial
    private static final long serialVersionUID = 9120415745622636175L;

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String jobTitle;

    @Column(length = 50)
    private String jobLevel;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @OneToMany(mappedBy = "employeePosition", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeePositionManagement> employeePositionManagements = new LinkedHashSet<>();

}
