package et.hrms.dal.repository.leave;

import et.hrms.dal.model.leave.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

  @Query("select lr from LeaveRequest lr join Employee e on lr.employee.id = e.id where e.id =:employeeId and lr.status = true ")
  LeaveRequest getLeaveRequestByEmployeeId(@Param("employeeId") Long employeeId);

  @Query("select lr from LeaveRequest lr join Employee e on lr.employee.id = e.id where e.id =:employeeId and lr.status =:status ")
  List<LeaveRequest> getLeaveRequestByStatusAndEmployeeId(@Param("employeeId") Long employeeId, @Param("status") boolean status);

}