package et.hrms.dal.repository;

import et.hrms.dal.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


}
