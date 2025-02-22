package com.gtltek.employee.core.dal.repository.onboarding;

import com.gtltek.employee.core.dal.model.onboarding.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
}
