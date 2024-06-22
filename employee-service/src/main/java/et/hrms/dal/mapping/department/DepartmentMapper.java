package et.hrms.dal.mapping.department;


import et.hrms.dal.dto.department.DepartmentDTO;
import et.hrms.dal.dto.department.DepartmentUnderBranchDTO;
import et.hrms.dal.dto.department.DepartmentUnderOrganizationDTO;
import et.hrms.dal.model.department.Department;
import et.hrms.dal.model.department.DepartmentUnderBranch;
import et.hrms.dal.model.department.DepartmentUnderOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    // Mappings for DepartmentUnderBranch
    DepartmentUnderBranch toDepartmentUnderBranch(DepartmentUnderBranchDTO dto);
    DepartmentUnderBranchDTO toDepartmentUnderBranchDTO(DepartmentUnderBranch entity);
    void updateDepartmentUnderBranchFromDTO(DepartmentUnderBranchDTO dto, @MappingTarget DepartmentUnderBranch entity);

    // Mappings for DepartmentUnderOrganization
    DepartmentUnderOrganization toDepartmentUnderOrganization(DepartmentUnderOrganizationDTO dto);
    DepartmentUnderOrganizationDTO toDepartmentUnderOrganizationDTO(DepartmentUnderOrganization entity);
    void updateDepartmentUnderOrganizationFromDTO(DepartmentUnderOrganizationDTO dto, @MappingTarget DepartmentUnderOrganization entity);

    DepartmentDTO toDepartmentDTO(DepartmentUnderBranch departmentUnderBranch);
    DepartmentDTO toDepartmentDTO(DepartmentUnderOrganization departmentUnderOrganization);

    DepartmentDTO toDepartment(Department department);


}
