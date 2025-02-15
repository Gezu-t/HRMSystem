package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import dal.dto.employee.FamilyDTO;
import dal.model.employee.Family;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    FamilyDTO toFamilyDTO(Family family);

    Family toFamily(FamilyDTO familyDTO);



}
