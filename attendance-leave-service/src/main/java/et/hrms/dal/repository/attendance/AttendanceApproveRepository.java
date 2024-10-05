package et.hrms.dal.repository.attendance;

import et.hrms.dal.model.attendance.AttendanceApprove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceApproveRepository extends JpaRepository<AttendanceApprove, Long> {
}
