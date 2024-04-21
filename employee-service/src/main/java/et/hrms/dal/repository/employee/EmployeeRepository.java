package et.hrms.dal.repository.employee;

import et.hrms.dal.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("select e from Employee e where e.employeeNumber = :employeeNumber")
    Employee findByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

}
