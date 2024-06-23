package com.hrmsystem.employeeprofileservice.dal.dto.onboarding;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class OnboardingDTO implements Serializable {
  @Serial
  private static final long serialVersionUID = -3497982675178199767L;


  private Long onboardingId;
  private String fullName;
  private LocalDate firstDate;
  private String materialAssigned;

  public Long getOnboardingId() {
    return onboardingId;
  }

  public void setOnboardingId(Long onboardingId) {
    this.onboardingId = onboardingId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public LocalDate getFirstDate() {
    return firstDate;
  }

  public void setFirstDate(LocalDate firstDate) {
    this.firstDate = firstDate;
  }

  public String getMaterialAssigned() {
    return materialAssigned;
  }

  public void setMaterialAssigned(String materialAssigned) {
    this.materialAssigned = materialAssigned;
  }
}
