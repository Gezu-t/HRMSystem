package com.gtltek.employee.core.dal.mapping.department;

import com.gtltek.employee.core.dal.mapping.employee.EmployeeMapper;
import com.gtltek.employee.core.dal.dto.department.DepartmentDTO;
import com.gtltek.employee.core.dal.model.department.Department;
import org.mapstruct.Mapper;
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

}


