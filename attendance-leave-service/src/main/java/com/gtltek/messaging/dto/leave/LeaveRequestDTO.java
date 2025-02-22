package com.gtltek.messaging.dto.leave;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gtltek.messaging.model.LeaveRequestEvents;
import com.gtltek.messaging.model.LeaveRequestStates;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO  implements Serializable {

  @Serial
  private static final long serialVersionUID = -1431412166751946589L;


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
  private Long departmentId;
  private Long branchId;
  private Long organizationId;

}
