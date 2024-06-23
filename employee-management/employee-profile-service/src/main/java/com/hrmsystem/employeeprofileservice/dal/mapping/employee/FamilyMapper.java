package com.hrmsystem.employeeprofileservice.dal.mapping.employee;


import com.hrmsystem.employeeservice.core.dal.model.employee.Family;
import com.hrmsystem.employeeprofileservice.dal.dto.employee.FamilyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "employeeId", source = "employee.id")
    FamilyDTO toFamilyDTO(Family family);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "employee.id", source = "employeeId")
    Family toFamily(FamilyDTO familyDTO);



}
