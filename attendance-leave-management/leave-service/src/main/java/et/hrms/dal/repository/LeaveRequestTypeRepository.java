package et.hrms.dal.repository;

import et.hrms.dal.model.LeaveRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestTypeRepository extends JpaRepository<LeaveRequestType, Long> {
}
