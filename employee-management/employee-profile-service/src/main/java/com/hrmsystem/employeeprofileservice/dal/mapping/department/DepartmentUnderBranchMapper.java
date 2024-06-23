package com.hrmsystem.employeeprofileservice.dal.mapping.department;

import com.hrmsystem.employeeprofileservice.dal.dto.department.DepartmentUnderBranchDTO;
import com.hrmsystem.employeeservice.core.dal.model.department.DepartmentUnderBranch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentUnderBranchMapper {
    DepartmentUnderBranch toDepartmentUnderBranch(DepartmentUnderBranchDTO dto);
    DepartmentUnderBranchDTO toDepartmentUnderBranchDTO(DepartmentUnderBranch entity);
    void updateDepartmentUnderBranchFromDTO(DepartmentUnderBranchDTO dto, @MappingTarget DepartmentUnderBranch entity);
}