package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import com.hrmsystem.employeeservice.core.dal.dto.employee.EmployeePromotionDTO;
import dal.model.employee.EmployeePromotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeePromotionMapper {

    EmployeePromotionMapper INSTANCE = Mappers.getMapper(EmployeePromotionMapper.class);

    EmployeePromotion toEmployeePromotion(EmployeePromotionDTO employeePromotionDTO);

    EmployeePromotionDTO toEmployeePromotionDTO(EmployeePromotion employeePromotion);



}
