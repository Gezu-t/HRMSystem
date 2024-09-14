package com.hrmsystem.employeeservice.core.dal.dto.education;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class EducationAwardDTO {
  private Long awardId;
  @NotNull(message = "Award name must not be null value")
  @Max(254)
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate date;
  private String description;

  public Long getAwardId() {
    return awardId;
  }

  public void setAwardId(Long awardId) {
    this.awardId = awardId;
  }

  public @NotNull(message = "Award name must not be null value") @Max(254) String getName() {
    return name;
  }

  public void setName(@NotNull(message = "Award name must not be null value") @Max(254) String name) {
    this.name = name;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
