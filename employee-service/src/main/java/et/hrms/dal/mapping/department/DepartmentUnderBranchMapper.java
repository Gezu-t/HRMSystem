package et.hrms.dal.mapping.department;

import et.hrms.dal.dto.department.DepartmentUnderBranchDTO;
import et.hrms.dal.model.department.DepartmentUnderBranch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentUnderBranchMapper {
    DepartmentUnderBranch toDepartmentUnderBranch(DepartmentUnderBranchDTO dto);
    DepartmentUnderBranchDTO toDepartmentUnderBranchDTO(DepartmentUnderBranch entity);
    void updateDepartmentUnderBranchFromDTO(DepartmentUnderBranchDTO dto, @MappingTarget DepartmentUnderBranch entity);
}