package et.hrms.dal.mapping;


import et.hrms.dal.dto.BranchDTO;
import et.hrms.dal.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {


    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);


    @Mapping(target = "organizationAddress", source = "organizationAddressDTO")
    @Mapping(target = "organizationAddress.id", source = "organizationAddressDTO.addressId")
    @Mapping(target = "organization.id", source = "organizationId")
    @Mapping(target = "id", source = "branchId")
    Branch toBranch(BranchDTO branchDTO);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "organizationAddressDTO", source = "organizationAddress")
    @Mapping(target = "organizationAddressDTO.addressId", source = "organizationAddress.id")
    @Mapping(target = "branchId", source = "id")
    BranchDTO toBranchDTO(Branch branch);

    List<BranchDTO> toBranchDTOs(List<Branch> branches);
}
