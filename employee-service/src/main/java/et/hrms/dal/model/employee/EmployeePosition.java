package et.hrms.dal.model.employee;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_position")
public class EmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String jobLevel;

    private LocalDateTime startDate;

    @OneToMany(mappedBy = "employeePosition", fetch = FetchType.LAZY)
    private Set<EmployeePositionManagement> employeePositionManagements;



}
