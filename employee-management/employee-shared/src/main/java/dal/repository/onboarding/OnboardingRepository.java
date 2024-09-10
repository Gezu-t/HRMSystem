package dal.repository.onboarding;

import dal.model.onboarding.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {
}
