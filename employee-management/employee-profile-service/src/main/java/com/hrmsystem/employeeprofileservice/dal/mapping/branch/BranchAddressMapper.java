package com.hrmsystem.employeeprofileservice.dal.mapping.branch;

import com.hrmsystem.employeeprofileservice.dal.dto.department.BranchAddressDTO;
import dal.model.branch.BranchAddress;
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