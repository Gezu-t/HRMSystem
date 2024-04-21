package et.hrms.dal.repository;

import et.hrms.dal.model.CandidateType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateTypeRepository extends JpaRepository<CandidateType, Long> {
}
