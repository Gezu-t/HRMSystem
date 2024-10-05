package et.hrms.service.leave;

import et.hrms.dal.dto.leave.LeaveBalanceDTO;

import java.util.List;

public interface LeaveBalanceService {
    LeaveBalanceDTO createLeaveBalance(LeaveBalanceDTO leaveBalanceDTO);

    LeaveBalanceDTO updateLeaveBalance(Long id, LeaveBalanceDTO leaveBalanceDTO);

    LeaveBalanceDTO getLeaveBalanceById(Long id);

    List<LeaveBalanceDTO> getAllLeaveBalances();
}
