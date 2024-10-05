package dal.dto.branch;


import dal.dto.common.AddressDTO;
import dal.dto.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 2158415358743257032L;

  private Long id;
  private String branchCode;
  private String branchName;
  private Long organizationId;
  private String organizationName;
  private List<DepartmentDTO> departments;
  private AddressDTO branchAddressDTO;

}
