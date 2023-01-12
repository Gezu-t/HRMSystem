package et.hrms.dal.repository;

import et.hrms.dal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeNumber(String employeeNumber);

}
