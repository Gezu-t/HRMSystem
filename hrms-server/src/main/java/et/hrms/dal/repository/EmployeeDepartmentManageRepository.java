package et.hrms.dal.repository;

import et.hrms.dal.model.EmployeeDepartmentManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EmployeeDepartmentManageRepository extends JpaRepository<EmployeeDepartmentManagement, Long> {



    Set<EmployeeDepartmentManagement> findByEmployeeId(long employeeId);
}
