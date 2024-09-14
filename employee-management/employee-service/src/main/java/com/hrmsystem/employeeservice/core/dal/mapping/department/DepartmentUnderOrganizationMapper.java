package com.hrmsystem.employeeservice.core.dal.mapping.department;

import com.hrmsystem.employeeservice.core.dal.dto.department.DepartmentUnderOrganizationDTO;
import dal.model.department.DepartmentUnderOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentUnderOrganizationMapper {
    DepartmentUnderOrganization toDepartmentUnderOrganization(DepartmentUnderOrganizationDTO dto);
    DepartmentUnderOrganizationDTO toDepartmentUnderOrganizationDTO(DepartmentUnderOrganization entity);
    void updateDepartmentUnderOrganizationFromDTO(DepartmentUnderOrganizationDTO dto, @MappingTarget DepartmentUnderOrganization entity);
}