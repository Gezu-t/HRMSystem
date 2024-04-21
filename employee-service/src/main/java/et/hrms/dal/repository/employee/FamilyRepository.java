package et.hrms.dal.repository.employee;

import et.hrms.dal.model.employee.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
