package et.hrms.dal.repository.leave;

import et.hrms.dal.model.leave.LeaveRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestTypeRepository extends JpaRepository<LeaveRequestType, Long> {
}
