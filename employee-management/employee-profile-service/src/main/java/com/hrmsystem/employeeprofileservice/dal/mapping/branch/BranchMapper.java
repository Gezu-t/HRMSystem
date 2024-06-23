package com.hrmsystem.employeeprofileservice.dal.mapping.branch;


import com.hrmsystem.employeeprofileservice.dal.dto.department.BranchDTO;
import com.hrmsystem.employeeservice.core.dal.model.branch.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);


    Branch toBranch(BranchDTO branchDTO);

    BranchDTO toBranchDTO(Branch branch);
    void updateBranchFromDTO(BranchDTO dto, @MappingTarget Branch entity);
    List<BranchDTO> toBranchDTOs(List<Branch> branches);

    Branch updateBranchFromDTO(@MappingTarget Branch branch, BranchDTO branchDTO);


}
