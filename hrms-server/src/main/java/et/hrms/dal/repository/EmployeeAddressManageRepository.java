package et.hrms.dal.repository;

import et.hrms.dal.model.EmployeeAddressManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressManageRepository extends JpaRepository<EmployeeAddressManagement, Long> {
}
