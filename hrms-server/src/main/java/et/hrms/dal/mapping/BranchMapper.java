package et.hrms.dal.mapping;


import et.hrms.dal.dto.structure.BranchDTO;
import et.hrms.dal.model.structure.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrganizationAddressMapper.class)
public interface BranchMapper {


  BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);


  //    @Mapping(target = "organizationAddress", source = "organizationAddressDTO")
//    @Mapping(target = "organizationAddress.id", source = "organizationAddressDTO.addressId")
//    @Mapping(target = "organization.id", source = "organizationId")
//    @Mapping(target = "id", source = "branchId")
  @Mapping(target = "organization", ignore = true)
  Branch toBranch(BranchDTO branchDTO);

  //    @Mapping(target = "organizationId", source = "organization.id")
//    @Mapping(target = "organizationAddressDTO", source = "organizationAddress")
//    @Mapping(target = "organizationAddressDTO.addressId", source = "organizationAddress.id")
//    @Mapping(target = "branchId", source = "id")
  @Mapping(target = "organizationAddressDTO", ignore = true)
  BranchDTO toBranchDTO(Branch branch);

  List<BranchDTO> toBranchDTOs(List<Branch> branches);

  @Mapping(target = "id", ignore = true)
  Branch updateBranchFromDTO(@MappingTarget Branch branch, BranchDTO branchDTO);


}
