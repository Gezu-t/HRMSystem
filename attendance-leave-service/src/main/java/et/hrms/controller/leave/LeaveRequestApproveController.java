package et.hrms.controller.leave;

import et.hrms.dal.dto.leave.LeaveRequestApproveDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface LeaveRequestApproveController {

  @PostMapping("/{employeeId}/{leaveRequestId}")
  ResponseEntity<Void> createLeaveRequestApproval(
          @PathVariable("employeeId") long employeeId,
          @PathVariable("leaveRequestId") Long leaveRequestId,
          @RequestBody LeaveRequestApproveDTO leaveRequestApproveDTO);

  @PutMapping("/{employeeId}/{leaveRequestId}")
  ResponseEntity<LeaveRequestApproveDTO> updateLeaveRequestApproval(
          @PathVariable("employeeId") long employeeId,
          @PathVariable("leaveRequestId") Long leaveRequestId,
          @RequestBody LeaveRequestApproveDTO leaveRequestApproveDTO);

  @GetMapping("/{leaveRequestApproveId}")
  ResponseEntity<LeaveRequestApproveDTO> getLeaveRequestApprovalById(@PathVariable("leaveRequestApproveId") Long leaveRequestApproveId);

  @GetMapping
  ResponseEntity<List<LeaveRequestApproveDTO>> getAllLeaveRequestApprovals();
}
