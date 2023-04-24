package et.hrms.dal.repository.recruitment;

import et.hrms.dal.model.recruitment.CandidateType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateTypeRepository extends JpaRepository<CandidateType, Long> {
}
