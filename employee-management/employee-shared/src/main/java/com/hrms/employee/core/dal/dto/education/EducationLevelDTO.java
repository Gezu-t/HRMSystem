package com.hrms.employee.core.dal.dto.education;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class EducationLevelDTO {
  @NotNull(message = "Education Level ID cannot be null")
  private Long educationLevelId;

  @NotBlank(message = "Education Level Name cannot be blank")
  @Max(255)
  private String educationLevelName;


  public Long getEducationLevelId() {
    return educationLevelId;
  }

  public void setEducationLevelId(Long educationLevelId) {
    this.educationLevelId = educationLevelId;
  }

  public @NotNull(message = "Education level name must not be null values") @Max(254) @Min(2) String getEducationLevelName() {
    return educationLevelName;
  }

  public void setEducationLevelName(@NotNull(message = "Education level name must not be null values") @Max(254) @Min(2) String educationLevelName) {
    this.educationLevelName = educationLevelName;
  }
}
