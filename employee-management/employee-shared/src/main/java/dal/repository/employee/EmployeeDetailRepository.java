package dal.repository.employee;

import dal.model.employee.EmployeeDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {

    @Query("""
            select distinct e from EmployeeDetail e
            where e.department.departmentName = :departmentName
            order by e.employee.employeeNumber""")
    List<EmployeeDetail> findEmployeeByDepartmentName(@Param("departmentName") String departmentName, Pageable pageable);
}
