package et.hrms.dal.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_promotion")
public class EmployeePromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateOfLastPromotion;
    private String dateOfLastIncrement;
    private String dateOfLastDecrement;
    private String dateOfLastSalaryChange;
    private String dateOfLastSalaryIncrement;
    private String dateOfLastSalaryDecrement;
    private String dateOfLastSalaryChangeReason;
    private Boolean promotionStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
