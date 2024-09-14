package com.hrmsystem.employeeservice.core.dal.dto.department;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 2158415358743257032L;

  private Long id;
  @NotEmpty
  private String branchCode;
  @NotEmpty
  private String branchName;
  @NotNull
  private Long organizationId;
  private String organizationName;
  private String organizationCode;
  @NotNull
  private BranchAddressDTO branchAddressDTO;

}
