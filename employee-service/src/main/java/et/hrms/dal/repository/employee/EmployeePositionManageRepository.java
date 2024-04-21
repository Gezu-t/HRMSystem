package et.hrms.dal.repository.employee;

import et.hrms.dal.model.employee.EmployeePositionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionManageRepository extends JpaRepository<EmployeePositionManagement, Long> {
}
