package et.hrms.dal.mapping.department;

import et.hrms.dal.dto.department.DepartmentUnderOrganizationDTO;
import et.hrms.dal.model.department.DepartmentUnderOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentUnderOrganizationMapper {
    DepartmentUnderOrganization toDepartmentUnderOrganization(DepartmentUnderOrganizationDTO dto);
    DepartmentUnderOrganizationDTO toDepartmentUnderOrganizationDTO(DepartmentUnderOrganization entity);
    void updateDepartmentUnderOrganizationFromDTO(DepartmentUnderOrganizationDTO dto, @MappingTarget DepartmentUnderOrganization entity);
}