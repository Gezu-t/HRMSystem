package et.hrms.dal.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
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
