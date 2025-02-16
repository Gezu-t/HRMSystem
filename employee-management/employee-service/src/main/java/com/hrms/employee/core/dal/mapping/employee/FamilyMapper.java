package com.hrms.employee.core.dal.mapping.employee;


import com.hrms.employee.core.dal.dto.employee.FamilyDTO;
import com.hrms.employee.core.dal.model.employee.Family;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);

    FamilyDTO toFamilyDTO(Family family);

    Family toFamily(FamilyDTO familyDTO);



}
