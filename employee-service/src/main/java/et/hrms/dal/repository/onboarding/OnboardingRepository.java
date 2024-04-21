package et.hrms.dal.repository.onboarding;

import et.hrms.dal.model.onboarding.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
}
