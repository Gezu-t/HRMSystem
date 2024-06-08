package et.hrms.dal.dto.structure;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;


public class BranchDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 2158415358743257032L;


  private Long branchId;
  @NotEmpty
  private String branchCode;
  @NotEmpty
  private String branchName;
  @NotNull
  private Long organizationId;
  @NotNull
  private OrganizationAddressDTO organizationAddressDTO;

  public Long getBranchId() {
    return branchId;
  }

  public void setBranchId(Long branchId) {
    this.branchId = branchId;
  }

  public @NotEmpty String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(@NotEmpty String branchCode) {
    this.branchCode = branchCode;
  }

  public @NotEmpty String getBranchName() {
    return branchName;
  }

  public void setBranchName(@NotEmpty String branchName) {
    this.branchName = branchName;
  }

  public @NotNull Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(@NotNull Long organizationId) {
    this.organizationId = organizationId;
  }

  public @NotNull OrganizationAddressDTO getOrganizationAddressDTO() {
    return organizationAddressDTO;
  }

  public void setOrganizationAddressDTO(@NotNull OrganizationAddressDTO organizationAddressDTO) {
    this.organizationAddressDTO = organizationAddressDTO;
  }
}
