package et.hrms.dal.repository.attendance;

import et.hrms.dal.model.attendance.AttendanceCertified;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceCertifiedRepository extends JpaRepository<AttendanceCertified, Long> {
}
