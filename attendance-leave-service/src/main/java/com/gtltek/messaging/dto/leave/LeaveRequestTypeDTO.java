package com.gtltek.messaging.dto.leave;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class LeaveRequestTypeDTO {

  private Long leaveTypeId;

  @NotBlank(message = "Leave type name must not be empty")
  @Size(max = 100, message = "Leave type name must not exceed 100 characters")
  private String leaveRequestTypeName;
}
