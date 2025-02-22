package com.gtltek.service;

import com.gtltek.messaging.dto.leave.LeaveBalanceDTO;

import java.util.List;

public interface LeaveBalanceService {
    LeaveBalanceDTO createLeaveBalance(LeaveBalanceDTO leaveBalanceDTO);

    LeaveBalanceDTO updateLeaveBalance(Long id, LeaveBalanceDTO leaveBalanceDTO);

    LeaveBalanceDTO getLeaveBalanceById(Long id);

    List<LeaveBalanceDTO> getAllLeaveBalances();
}
