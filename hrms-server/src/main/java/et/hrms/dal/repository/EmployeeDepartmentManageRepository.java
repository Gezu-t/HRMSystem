package et.hrms.dal.repository;

import et.hrms.dal.model.EmployeeDepartmentManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDepartmentManageRepository extends JpaRepository<EmployeeDepartmentManagement, Long> {
}
