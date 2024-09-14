package com.hrmsystem.employeeservice.core.dal.dto.education;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO {

    private Long educationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate completionDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    @NotNull(message = "Institution name must not be null")
    @Max(254)
    private String institution;
    @NotNull(message = "Education level must not be null values")
    private Long educationLevelId;
    private Long educationAwardId;
    private List<EducationDetailDTO> educationDetailDTOS;
    private Long employeeId;

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotNull(message = "Institution name must not be null") @Max(254) String getInstitution() {
        return institution;
    }

    public void setInstitution(@NotNull(message = "Institution name must not be null") @Max(254) String institution) {
        this.institution = institution;
    }

    public @NotNull(message = "Education level must not be null values") Long getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(@NotNull(message = "Education level must not be null values") Long educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public Long getEducationAwardId() {
        return educationAwardId;
    }

    public void setEducationAwardId(Long educationAwardId) {
        this.educationAwardId = educationAwardId;
    }

    public List<EducationDetailDTO> getEducationDetailDTOS() {
        return educationDetailDTOS;
    }

    public void setEducationDetailDTOS(List<EducationDetailDTO> educationDetailDTOS) {
        this.educationDetailDTOS = educationDetailDTOS;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
