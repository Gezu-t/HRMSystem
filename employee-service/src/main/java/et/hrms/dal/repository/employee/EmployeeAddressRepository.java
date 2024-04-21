package et.hrms.dal.repository.employee;

import et.hrms.dal.model.employee.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long>{
}