package et.hrms.dal.dto.onboarding;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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

  public Long getMaterialAssignedId() {
    return materialAssignedId;
  }

  public void setMaterialAssignedId(Long materialAssignedId) {
    this.materialAssignedId = materialAssignedId;
  }

  public String getMaterialName() {
    return materialName;
  }

  public void setMaterialName(String materialName) {
    this.materialName = materialName;
  }

  public MaterialTypeDTO getMaterialTypeDTO() {
    return materialTypeDTO;
  }

  public void setMaterialTypeDTO(MaterialTypeDTO materialTypeDTO) {
    this.materialTypeDTO = materialTypeDTO;
  }

  public String getMaterialCode() {
    return materialCode;
  }

  public void setMaterialCode(String materialCode) {
    this.materialCode = materialCode;
  }

  public String getMaterialSerialCode() {
    return materialSerialCode;
  }

  public void setMaterialSerialCode(String materialSerialCode) {
    this.materialSerialCode = materialSerialCode;
  }

  public String getMaterialSpecification() {
    return materialSpecification;
  }

  public void setMaterialSpecification(String materialSpecification) {
    this.materialSpecification = materialSpecification;
  }

  public LocalDate getManufacturingDate() {
    return manufacturingDate;
  }

  public void setManufacturingDate(LocalDate manufacturingDate) {
    this.manufacturingDate = manufacturingDate;
  }

  public String getMaterialStatus() {
    return materialStatus;
  }

  public void setMaterialStatus(String materialStatus) {
    this.materialStatus = materialStatus;
  }

  public String getMaterialDescription() {
    return materialDescription;
  }

  public void setMaterialDescription(String materialDescription) {
    this.materialDescription = materialDescription;
  }
}
