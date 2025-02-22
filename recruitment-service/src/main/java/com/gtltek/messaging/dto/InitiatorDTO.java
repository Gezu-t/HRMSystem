package com.gtltek.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor@NoArgsConstructor
public class InitiatorDTO {

  private Long initiatorId;
  private String name;
  private String position;
  private String department;
}
