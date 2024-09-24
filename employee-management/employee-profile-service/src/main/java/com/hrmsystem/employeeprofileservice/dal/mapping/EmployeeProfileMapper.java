package com.hrmsystem.employeeprofileservice.dal.mapping;


import com.hrmsystem.employeeprofileservice.dal.dto.EmployeeProfileDTO;
import com.hrmsystem.employeeprofileservice.dal.model.EmployeeProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeProfileMapper {

    EmployeeProfileMapper INSTANCE = Mappers.getMapper(EmployeeProfileMapper.class);

    // Map EmployeeProfile entity to EmployeeProfileDTO
    EmployeeProfileDTO toEmployeeProfileDTO(EmployeeProfile employeeProfile);

    // Map EmployeeProfileDTO to EmployeeProfile entity
    @Mapping(target = "id", ignore = true) // Assuming id is in BaseEntity and auto-generated
    EmployeeProfile toEmployeeProfile(EmployeeProfileDTO employeeProfileDTO);
}
