package et.hrms.dal.dto.leave;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateLeaveRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long leaveTypeId;
    private String leaveRequestReason;
    private Long employeeId;
    private Long taskId;
    private Long projectId;

}

