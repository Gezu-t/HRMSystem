package com.hrmsystem.employeeservice.core.dal.mapping.employee;


import dal.dto.employee.EmployeePromotionDTO;
import dal.model.employee.EmployeePromotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeePromotionMapper {

    EmployeePromotionMapper INSTANCE = Mappers.getMapper(EmployeePromotionMapper.class);

    EmployeePromotion toEmployeePromotion(EmployeePromotionDTO employeePromotionDTO);

    EmployeePromotionDTO toEmployeePromotionDTO(EmployeePromotion employeePromotion);



}
