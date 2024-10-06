package dal.repository.branch;

import dal.model.branch.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Page<Branch> findByOrganizationId(Long organizationId, Pageable pageable);

    @Query("SELECT DISTINCT b FROM Branch b " +
            "LEFT JOIN FETCH b.addresses " +
            "LEFT JOIN FETCH b.organization " +
            "WHERE b.id = :branchId")
    Optional<Branch> findByIdWithAddressesAndOrganization(@Param("branchId") Long branchId);

}
