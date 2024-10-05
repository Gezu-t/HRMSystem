package dal.repository.department;

import dal.model.department.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByOrganizationIdAndBranchIsNull(Long organizationId, Sort sort);

    List<Department> findByBranchId(Long branchId, Sort sort);
}
