package com.hrmsystem.employeeservice.core.dal.dto.onboarding;

import java.io.Serial;
import java.io.Serializable;

public class MaterialTypeDTO implements Serializable {
  @Serial
  private static final long serialVersionUID = -3005354808830296716L;


  private Long materialTypeId;
  private String materialName;

  public Long getMaterialTypeId() {
    return materialTypeId;
  }

  public void setMaterialTypeId(Long materialTypeId) {
    this.materialTypeId = materialTypeId;
  }

  public String getMaterialName() {
    return materialName;
  }

  public void setMaterialName(String materialName) {
    this.materialName = materialName;
  }
}
