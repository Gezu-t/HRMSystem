package et.hrms.dal.mapping.department;

import et.hrms.dal.dto.department.BranchAddressDTO;
import et.hrms.dal.model.department.BranchAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BranchAddressMapper {

    BranchAddressMapper INSTANCE = Mappers.getMapper(BranchAddressMapper.class);

    @Mapping(target = "id", source = "id")
    BranchAddress toBranchAddress(BranchAddressDTO branchAddressDTO);

    @Mapping(target = "id", source = "id")
    BranchAddressDTO toBranchAddressDTO(BranchAddress branchAddress);

    void updateBranchAddressFromDto(BranchAddressDTO branchAddressDTO,
                                          @MappingTarget BranchAddress existingBranchAddress);

}