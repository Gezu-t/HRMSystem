package et.hrms.dal.repository;

import et.hrms.dal.model.EmployeePositionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionManageRepository extends JpaRepository<EmployeePositionManagement, Long> {
}
