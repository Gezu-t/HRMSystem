package et.hrms.dal.dto.structure;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

  private Long branchId;
  @NotEmpty
  private String branchCode;
  @NotEmpty
  private String branchName;
  @NotNull
  private Long organizationId;
  @NotNull
  private OrganizationAddressDTO organizationAddressDTO;

}
