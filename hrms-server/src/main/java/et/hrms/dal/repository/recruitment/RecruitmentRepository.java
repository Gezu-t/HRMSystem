package et.hrms.dal.repository.recruitment;

import et.hrms.dal.model.recruitment.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
