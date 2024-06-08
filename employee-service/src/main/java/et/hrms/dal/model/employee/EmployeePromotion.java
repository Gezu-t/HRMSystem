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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfLastPromotion() {
        return dateOfLastPromotion;
    }

    public void setDateOfLastPromotion(String dateOfLastPromotion) {
        this.dateOfLastPromotion = dateOfLastPromotion;
    }

    public String getDateOfLastIncrement() {
        return dateOfLastIncrement;
    }

    public void setDateOfLastIncrement(String dateOfLastIncrement) {
        this.dateOfLastIncrement = dateOfLastIncrement;
    }

    public String getDateOfLastDecrement() {
        return dateOfLastDecrement;
    }

    public void setDateOfLastDecrement(String dateOfLastDecrement) {
        this.dateOfLastDecrement = dateOfLastDecrement;
    }

    public String getDateOfLastSalaryChange() {
        return dateOfLastSalaryChange;
    }

    public void setDateOfLastSalaryChange(String dateOfLastSalaryChange) {
        this.dateOfLastSalaryChange = dateOfLastSalaryChange;
    }

    public String getDateOfLastSalaryIncrement() {
        return dateOfLastSalaryIncrement;
    }

    public void setDateOfLastSalaryIncrement(String dateOfLastSalaryIncrement) {
        this.dateOfLastSalaryIncrement = dateOfLastSalaryIncrement;
    }

    public String getDateOfLastSalaryDecrement() {
        return dateOfLastSalaryDecrement;
    }

    public void setDateOfLastSalaryDecrement(String dateOfLastSalaryDecrement) {
        this.dateOfLastSalaryDecrement = dateOfLastSalaryDecrement;
    }

    public String getDateOfLastSalaryChangeReason() {
        return dateOfLastSalaryChangeReason;
    }

    public void setDateOfLastSalaryChangeReason(String dateOfLastSalaryChangeReason) {
        this.dateOfLastSalaryChangeReason = dateOfLastSalaryChangeReason;
    }

    public Boolean getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Boolean promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
