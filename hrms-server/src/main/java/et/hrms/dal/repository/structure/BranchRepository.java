package et.hrms.dal.repository.structure;

import et.hrms.dal.model.structure.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
