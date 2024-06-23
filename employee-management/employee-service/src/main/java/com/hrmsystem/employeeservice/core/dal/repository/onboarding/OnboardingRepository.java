package com.hrmsystem.employeeservice.core.dal.repository.onboarding;

import com.hrmsystem.employeeservice.core.dal.model.onboarding.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
}
