package com.gtltek.employee.core.dal.mapping.employee;

import com.gtltek.employee.core.dal.dto.employee.EmployeeDetailDTO;
import com.gtltek.employee.core.dal.model.employee.EmployeeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeDetailMapper {

    EmployeeDetailMapper INSTANCE = Mappers.getMapper(EmployeeDetailMapper.class);


    EmployeeDetail toEmployeeDetail(EmployeeDetailDTO employeeDetailDTO);

    EmployeeDetailDTO toEmployeeDetailDTO(EmployeeDetail employeeDetail);

    List<EmployeeDetailDTO> toEmployeeDetailDTOs (List<EmployeeDetail> employeeDetails);
}
