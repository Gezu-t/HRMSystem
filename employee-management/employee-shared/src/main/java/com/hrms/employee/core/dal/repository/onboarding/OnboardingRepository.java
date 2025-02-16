package com.hrms.employee.core.dal.repository.onboarding;

import com.hrms.employee.core.dal.model.onboarding.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
}
