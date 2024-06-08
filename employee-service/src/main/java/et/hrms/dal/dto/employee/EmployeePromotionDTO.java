package et.hrms.dal.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePromotionDTO {

    private Long employeePromotionId;
    private String employeePromotionReason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfPromotion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfLastPromotion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfLastSalaryChange;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfLastSalaryIncrement;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfLastSalaryDecrement;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfLastSalaryChangeReason;
    private Boolean promotionStatus;

    public Long getEmployeePromotionId() {
        return employeePromotionId;
    }

    public void setEmployeePromotionId(Long employeePromotionId) {
        this.employeePromotionId = employeePromotionId;
    }

    public String getEmployeePromotionReason() {
        return employeePromotionReason;
    }

    public void setEmployeePromotionReason(String employeePromotionReason) {
        this.employeePromotionReason = employeePromotionReason;
    }

    public LocalDate getDateOfPromotion() {
        return dateOfPromotion;
    }

    public void setDateOfPromotion(LocalDate dateOfPromotion) {
        this.dateOfPromotion = dateOfPromotion;
    }

    public LocalDate getDateOfLastPromotion() {
        return dateOfLastPromotion;
    }

    public void setDateOfLastPromotion(LocalDate dateOfLastPromotion) {
        this.dateOfLastPromotion = dateOfLastPromotion;
    }

    public LocalDate getDateOfLastSalaryChange() {
        return dateOfLastSalaryChange;
    }

    public void setDateOfLastSalaryChange(LocalDate dateOfLastSalaryChange) {
        this.dateOfLastSalaryChange = dateOfLastSalaryChange;
    }

    public LocalDate getDateOfLastSalaryIncrement() {
        return dateOfLastSalaryIncrement;
    }

    public void setDateOfLastSalaryIncrement(LocalDate dateOfLastSalaryIncrement) {
        this.dateOfLastSalaryIncrement = dateOfLastSalaryIncrement;
    }

    public LocalDate getDateOfLastSalaryDecrement() {
        return dateOfLastSalaryDecrement;
    }

    public void setDateOfLastSalaryDecrement(LocalDate dateOfLastSalaryDecrement) {
        this.dateOfLastSalaryDecrement = dateOfLastSalaryDecrement;
    }

    public LocalDate getDateOfLastSalaryChangeReason() {
        return dateOfLastSalaryChangeReason;
    }

    public void setDateOfLastSalaryChangeReason(LocalDate dateOfLastSalaryChangeReason) {
        this.dateOfLastSalaryChangeReason = dateOfLastSalaryChangeReason;
    }

    public Boolean getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Boolean promotionStatus) {
        this.promotionStatus = promotionStatus;
    }
}
