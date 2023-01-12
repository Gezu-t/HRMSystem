package et.hrms.dal.repository;

import et.hrms.dal.model.EmployeeAddressManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EmployeeAddressManageRepository extends JpaRepository<EmployeeAddressManagement, Long> {


    Set<EmployeeAddressManagement> findByEmployeeId(long employeeId);

}
