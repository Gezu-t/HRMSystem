package et.hrms.dal.repository.employee;

import et.hrms.dal.model.employee.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
}
