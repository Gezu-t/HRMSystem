package com.hrms.employee.core.dal.mapping.employee;


import com.hrms.employee.core.dal.dto.employee.EmployeePromotionDTO;
import com.hrms.employee.core.dal.model.employee.EmployeePromotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeePromotionMapper {

    EmployeePromotionMapper INSTANCE = Mappers.getMapper(EmployeePromotionMapper.class);

    EmployeePromotion toEmployeePromotion(EmployeePromotionDTO employeePromotionDTO);

    EmployeePromotionDTO toEmployeePromotionDTO(EmployeePromotion employeePromotion);



}
