package et.hrms.dal.repository.attendance;

import et.hrms.dal.model.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


}
