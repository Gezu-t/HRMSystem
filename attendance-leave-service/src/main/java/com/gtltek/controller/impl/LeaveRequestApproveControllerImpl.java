package com.gtltek.controller.impl;

import com.gtltek.controller.LeaveRequestApproveController;
import com.gtltek.messaging.dto.leave.LeaveRequestApproveDTO;
import com.gtltek.service.LeaveRequestApproveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/leaveRequestApproves")
public class LeaveRequestApproveControllerImpl implements LeaveRequestApproveController {

  private final LeaveRequestApproveService leaveRequestApproveService;

    public LeaveRequestApproveControllerImpl(LeaveRequestApproveService leaveRequestApproveService) {
        this.leaveRequestApproveService = leaveRequestApproveService;
    }

    @Override
  @PostMapping("/{employeeId}/{leaveRequestId}")
  public ResponseEntity<Void> createLeaveRequestApproval(
          @PathVariable("employeeId") long employeeId,
          @PathVariable("leaveRequestId") Long leaveRequestId,
          @RequestBody LeaveRequestApproveDTO leaveRequestApproveDTO) {
    leaveRequestApproveService.createLeaveRequestApprove(employeeId, leaveRequestId, leaveRequestApproveDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  @PutMapping("/{employeeId}/{leaveRequestId}")
  public ResponseEntity<LeaveRequestApproveDTO> updateLeaveRequestApproval(
          @PathVariable("employeeId") long employeeId,
          @PathVariable("leaveRequestId") Long leaveRequestId,
          @RequestBody LeaveRequestApproveDTO leaveRequestApproveDTO) {
    LeaveRequestApproveDTO updatedLeaveRequestApproveDTO = leaveRequestApproveService.updateLeaveRequestApprove(employeeId, leaveRequestId, leaveRequestApproveDTO);
    return ResponseEntity.ok(updatedLeaveRequestApproveDTO);
  }

  @Override
  @GetMapping("/{leaveRequestApproveId}")
  public ResponseEntity<LeaveRequestApproveDTO> getLeaveRequestApprovalById(@PathVariable("leaveRequestApproveId") Long leaveRequestApproveId) {
    LeaveRequestApproveDTO leaveRequestApproveDTO = leaveRequestApproveService.findById(leaveRequestApproveId);
    return ResponseEntity.ok(leaveRequestApproveDTO);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<LeaveRequestApproveDTO>> getAllLeaveRequestApprovals() {
    List<LeaveRequestApproveDTO> leaveRequestApproveDTOList = leaveRequestApproveService.findAll();
    return ResponseEntity.ok(leaveRequestApproveDTOList);
  }

}
