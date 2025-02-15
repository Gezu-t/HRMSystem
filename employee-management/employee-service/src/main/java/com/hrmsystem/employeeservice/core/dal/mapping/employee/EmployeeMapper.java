package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeeDTO;
import com.hrmsystem.employeeservice.core.dal.mapping.education.EducationMapper;
import dal.model.GenderType;
import dal.model.MaritalStatus;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.employee.Employee;
import dal.model.employee.EmployeeType;
import dal.model.organization.Organization;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        EmployeeAppearanceMapper.class,
        FamilyMapper.class,
        EmployeeEvaluationMapper.class,
        EmployeeAddressMapper.class,
        EmployeeDetailMapper.class,
        EmployeePromotionMapper.class,
        EducationMapper.class
})
public interface EmployeeMapper {

    EmployeeDTO toEmployeeDTO(Employee employee);


    Employee toEmployee(EmployeeDTO employeeDTO);

    void updateEmployee(EmployeeDTO employeeDTO, @MappingTarget Employee employee);

    @AfterMapping
    default void setEnumAndEntityValues(EmployeeDTO dto, @MappingTarget Employee entity) {
        if (dto.getGenderStatus() != null) {
            entity.setGenderStatus(GenderType.valueOf(dto.getGenderStatus()));
        }
        if (dto.getMaritalStatus() != null) {
            entity.setMaritalStatus(MaritalStatus.valueOf(dto.getMaritalStatus()));
        }
        if (dto.getEmployeeType() != null) {
            entity.setEmployeeType(EmployeeType.valueOf(dto.getEmployeeType()));
        }
        if (dto.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(dto.getDepartmentId());
            entity.setDepartment(department);
        }
        if (dto.getBranchId() != null) {
            Branch branch = new Branch();
            branch.setId(dto.getBranchId());
            entity.setBranch(branch);
        }
        if (dto.getOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setId(dto.getOrganizationId());
            entity.setOrganization(organization);
        }
    }
}
