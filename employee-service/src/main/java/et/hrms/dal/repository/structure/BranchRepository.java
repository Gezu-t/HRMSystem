package et.hrms.dal.repository.structure;

import et.hrms.dal.model.department.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Page<Branch> findByOrganizationId(Long organizationId, Pageable pageable);

}
