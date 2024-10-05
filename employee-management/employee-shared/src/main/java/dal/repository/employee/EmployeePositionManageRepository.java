package dal.repository.employee;

import dal.model.employee.EmployeePositionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionManageRepository extends JpaRepository<EmployeePositionManagement, Long> {
}
