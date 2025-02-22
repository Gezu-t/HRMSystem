package com.gtltek.messaging.repository;

import com.gtltek.messaging.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employeeId = :employeeId AND lr.status = true")
    LeaveRequest findActiveLeaveRequestByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employeeId = :employeeId AND lr.status = :status")
    List<LeaveRequest> findLeaveRequestsByEmployeeIdAndStatus(@Param("employeeId") Long employeeId, @Param("status") boolean status);
}
