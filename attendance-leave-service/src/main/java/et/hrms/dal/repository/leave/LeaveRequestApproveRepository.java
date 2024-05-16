package et.hrms.dal.repository.leave;

import et.hrms.dal.model.leave.LeaveRequestApprove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestApproveRepository extends JpaRepository<LeaveRequestApprove, Long> {
}
