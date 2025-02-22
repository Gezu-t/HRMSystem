package com.gtltek.messaging.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceApproveDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 7981402252791258800L;


  private Long attendanceApproveId;
  private String approvedBy;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate approvedDate;
  private String description;
}
