package et.hrms.dal.repository;

import et.hrms.dal.model.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select distinct d from Department d where d.organization.id = :id order by d.departmentName")
    List<Department> findByOrganizationId(@Param("id") Long id, Sort sort);

    @Query("select distinct d from Department d where d.branch.id = :id order by d.departmentName")
    List<Department> findByBranchId(@Param("id") Long id, Sort sort);



}
