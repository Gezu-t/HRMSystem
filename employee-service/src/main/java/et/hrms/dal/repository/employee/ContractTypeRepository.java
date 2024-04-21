package et.hrms.dal.repository.employee;

import et.hrms.dal.model.employee.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
}
