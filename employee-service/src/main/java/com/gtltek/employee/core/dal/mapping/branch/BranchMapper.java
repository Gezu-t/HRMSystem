package com.gtltek.employee.core.dal.mapping.branch;

import com.gtltek.employee.core.dal.dto.branch.BranchDTO;
import com.gtltek.employee.core.dal.model.branch.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);


    BranchDTO toBranchDTO(Branch entity);

    Branch toBranch(BranchDTO dto);

    void updateBranch(BranchDTO dto, @MappingTarget Branch entity);

    List<BranchDTO> toBranchDTOList(List<Branch> entities);

}