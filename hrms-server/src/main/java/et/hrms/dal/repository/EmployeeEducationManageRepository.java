package et.hrms.dal.repository;

import et.hrms.dal.model.EmployeeEducationManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEducationManageRepository extends JpaRepository<EmployeeEducationManagement, Long> {
}
