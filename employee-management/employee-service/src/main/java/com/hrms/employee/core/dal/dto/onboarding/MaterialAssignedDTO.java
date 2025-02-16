package com.hrms.employee.core.dal.dto.onboarding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAssignedDTO implements Serializable {
  @Serial
  private static final long serialVersionUID = -6478850776069030804L;


  private Long materialAssignedId;
  private String materialName;
  private MaterialTypeDTO materialTypeDTO;
  private String materialCode;
  private String materialSerialCode;
  private String materialSpecification;
  private LocalDate manufacturingDate;
  private String materialStatus;
  private String materialDescription;

}
