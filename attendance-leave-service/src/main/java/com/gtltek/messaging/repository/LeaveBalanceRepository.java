package com.gtltek.messaging.repository;
import com.gtltek.messaging.model.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    Optional<LeaveBalance> findByEmployeeId(Long employeeId);
}