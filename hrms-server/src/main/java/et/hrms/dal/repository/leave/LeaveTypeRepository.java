package et.hrms.dal.repository.leave;

import et.hrms.dal.model.leave.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
