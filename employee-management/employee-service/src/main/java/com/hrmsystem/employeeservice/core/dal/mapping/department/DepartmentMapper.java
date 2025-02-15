package com.hrmsystem.employeeservice.core.dal.mapping.department;

import com.hrmsystem.employeeservice.core.dal.mapping.employee.EmployeeMapper;
import dal.dto.department.DepartmentDTO;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.organization.Organization;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO toDepartmentDTO(Department entity);
    Department toDepartment(DepartmentDTO dto);
    void updateDepartmentFromDTO(DepartmentDTO dto, @MappingTarget Department entity);
    List<DepartmentDTO> toDepartmentDTOList(List<Department> entities);

    @AfterMapping
    default void setBranchAndOrganization(DepartmentDTO dto, @MappingTarget Department entity) {
        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            entity.setBranch(branch);

            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            branch.setOrganization(organization);
            entity.setOrganization(organization);
        } else if (dto.getOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            entity.setOrganization(organization);

            entity.setBranch(null);
        }
    }

    // After mapping logic for additional fields
    @AfterMapping
    default void setAdditionalFields(Department entity, @MappingTarget DepartmentDTO dto) {
        if (entity.getBranch() != null) {
            if (entity.getBranch().getOrganization() != null) {
                dto.setOrganizationId(entity.getBranch().getOrganization().getId());
            }
        }
    }
}