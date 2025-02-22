package com.gtltek.service;

import com.gtltek.messaging.dto.leave.LeaveRequestApproveDTO;

import java.util.List;

public interface LeaveRequestApproveService {

  void createLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO);

  LeaveRequestApproveDTO updateLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO);

  LeaveRequestApproveDTO findById(Long leaveRequestApproveId);

  List<LeaveRequestApproveDTO> findAll();
}
