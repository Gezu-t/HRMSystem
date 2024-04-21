package et.hrms.dal.dto.leave;

import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.dto.leave.LeaveRequestTypeDTO;
import et.hrms.dal.model.leave.LeaveRequestEvents;
import et.hrms.dal.model.leave.LeaveRequestStates;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {

  private Long leaveRequestId;

  @NotNull(message = "Start date cannot be null")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate startDate;


  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate endDate;

  private LeaveRequestTypeDTO leaveRequestTypeDTO;
  private LeaveRequestEvents leaveRequestEvents;
  private LeaveRequestStates leaveRequestStates;

  private Long employeeId;
  private Long leaveBalanceId;
  private Long taskId;
  private Long projectId;

  private String leaveStatus;
  private Boolean status;


  private String employeeName;
  private String employeeDepartment;
  private String department;
  private String leaveRequestReason;
  private String approverComments;
}
