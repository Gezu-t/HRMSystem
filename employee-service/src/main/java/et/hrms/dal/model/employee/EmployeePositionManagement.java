package et.hrms.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@Entity
@Table(name = "employee_position_management")
public class EmployeePositionManagement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "employeePositionId")
    private EmployeePosition employeePosition;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean creationStatus;
    private Boolean updateStatus;


}
