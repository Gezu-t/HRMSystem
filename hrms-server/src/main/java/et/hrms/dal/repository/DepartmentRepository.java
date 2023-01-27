package et.hrms.dal.repository;

import et.hrms.dal.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {



}
