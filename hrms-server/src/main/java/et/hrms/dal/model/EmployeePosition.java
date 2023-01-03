package et.hrms.dal.model;


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
    @SequenceGenerator(name = "employeePosition_id_gen",
            sequenceName = "employeePosition_id_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employeePosition_id_gen")
    private Long id;

    private String jobTitle;
    private String jobLevel;

    private LocalDateTime startDate;

    @OneToMany(mappedBy = "employeePosition", fetch = FetchType.LAZY)
    private Set<EmployeePositionManagement> employeePositionManagements;



}
