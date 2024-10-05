package com.hrmsystem.employeeprofileservice.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DateManager {
  private LocalDate date;
  private String data;
  private boolean isWeekend;


}
