package et.hrms.dal.repository.leave;

import et.hrms.dal.model.leave.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}
