package com.hrms.employee.core.dal.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDetailDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 4038191925997764904L;

  private Long educationDetailId;
  private Long educationId;
  private String degree;
  private String major;
  private String minor;
  private String grade;
  private String gradePoint;
  private String gpa;
  private String gpaOutOf;
  private String gpaOutOfScale;
  private Year academicYear;
  private Long educationTypeId;

}
