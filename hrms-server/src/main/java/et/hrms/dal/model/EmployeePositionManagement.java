package et.hrms.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee_position_management")
public class EmployeePositionManagement {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_position_management_gen")
    @SequenceGenerator(name = "employee_position_management_gen", sequenceName = "employee_position_management_seq", allocationSize = 1)
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
