package com.gtltek.messaging.dto.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLeaveRequestDTO  implements Serializable  {

    @Serial
    private static final long serialVersionUID = 3062671967791603386L;

    private LocalDate startDate;
    private LocalDate endDate;
    private Long leaveTypeId;
    private String leaveRequestReason;
    private Long employeeId;
    private Long taskId;
    private Long projectId;


}

