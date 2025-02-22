package com.gtltek.service.impl;

import com.gtltek.client.employee.EmployeeClientService;
import et.hrms.messaging.dto.employee.EmployeeLeaveDTO;
import com.gtltek.messaging.dto.leave.LeaveRequestApproveDTO;
import com.gtltek.messaging.mapper.LeaveRequestApproveMapper;
import com.gtltek.messaging.model.LeaveRequest;
import com.gtltek.messaging.model.LeaveRequestApprove;
import com.gtltek.messaging.repository.LeaveRequestApproveRepository;
import com.gtltek.messaging.repository.LeaveRequestRepository;
import com.gtltek.service.LeaveRequestApproveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestApproveServiceImpl implements LeaveRequestApproveService {

  private static final Logger logger = LoggerFactory.getLogger(LeaveRequestApproveServiceImpl.class);

  private final LeaveRequestApproveRepository leaveRequestApproveRepository;
  private final LeaveRequestRepository leaveRequestRepository;
  private final EmployeeClientService employeeClientService;
  private final LeaveRequestApproveMapper leaveRequestApproveMapper;

  public LeaveRequestApproveServiceImpl(LeaveRequestApproveRepository leaveRequestApproveRepository,
                                        LeaveRequestRepository leaveRequestRepository,
                                        EmployeeClientService employeeClientService,
                                        LeaveRequestApproveMapper leaveRequestApproveMapper) {
    this.leaveRequestApproveRepository = leaveRequestApproveRepository;
    this.leaveRequestRepository = leaveRequestRepository;
    this.employeeClientService = employeeClientService;
    this.leaveRequestApproveMapper = leaveRequestApproveMapper;
  }

  @Override
  @Transactional
  public void createLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO) {
    logger.debug("Attempting to create leave request approval for leaveRequestId: {} by employeeId: {}", leaveRequestId, employeeId);
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new RuntimeException("Leave request not found: " + leaveRequestId));

    EmployeeLeaveDTO approvedBy = employeeClientService.getEmployeeById(employeeId);
    validateEmployeeStatus(approvedBy);

    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveMapper.toEntity(leaveRequestApproveDTO);
    leaveRequestApprove.setLeaveRequest(leaveRequest);
    leaveRequestApprove.setApprovedByEmployeeId(approvedBy.getId());

    leaveRequestApproveRepository.save(leaveRequestApprove);
    logger.info("Leave request approval created successfully for leaveRequestId: {}", leaveRequestId);
  }

  @Override
  @Transactional
  public LeaveRequestApproveDTO updateLeaveRequestApprove(long employeeId, Long leaveRequestId, LeaveRequestApproveDTO leaveRequestApproveDTO) {
    logger.debug("Updating leave request approval for id: {}", leaveRequestApproveDTO.getLeaveRequestApproveId());
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new RuntimeException("Leave request not found: " + leaveRequestId));

    EmployeeLeaveDTO approvedBy = employeeClientService.getEmployeeById(employeeId);
    validateEmployeeStatus(approvedBy);

    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveRepository.findById(leaveRequestApproveDTO.getLeaveRequestApproveId())
            .orElseThrow(() -> new RuntimeException("Leave request approval not found: " + leaveRequestApproveDTO.getLeaveRequestApproveId()));

    leaveRequestApprove.setLeaveRequest(leaveRequest);
    leaveRequestApprove.setApprovedByEmployeeId(approvedBy.getId());
    leaveRequestApproveRepository.save(leaveRequestApprove);

    logger.info("Leave request approval updated successfully for id: {}", leaveRequestApprove.getId());
    return leaveRequestApproveMapper.toDTO(leaveRequestApprove);
  }

  private void validateEmployeeStatus(EmployeeLeaveDTO employee) {
    if (employee.getStatus() == null || !employee.getStatus().equalsIgnoreCase("Active")) {
      logger.error("Employee status is not active. EmployeeId: {}", employee.getId());
      throw new IllegalStateException("Employee status is not active for approval");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public LeaveRequestApproveDTO findById(Long leaveRequestApproveId) {
    LeaveRequestApprove leaveRequestApprove = leaveRequestApproveRepository.findById(leaveRequestApproveId)
            .orElseThrow(() -> new RuntimeException("Leave request approval not found: " + leaveRequestApproveId));

    return leaveRequestApproveMapper.toDTO(leaveRequestApprove);
  }

  @Override
  @Transactional(readOnly = true)
  public List<LeaveRequestApproveDTO> findAll() {
    List<LeaveRequestApprove> leaveRequestApproves = leaveRequestApproveRepository.findAll();
    return leaveRequestApproves.stream()
            .map(leaveRequestApproveMapper::toDTO)
            .collect(Collectors.toList());
  }
}
