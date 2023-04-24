package et.hrms.dal.repository.recruitment;

import et.hrms.dal.model.recruitment.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
