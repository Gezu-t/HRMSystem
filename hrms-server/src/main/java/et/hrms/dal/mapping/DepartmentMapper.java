package et.hrms.dal.mapping;


import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department toDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO toDepartmentDTO(Department department);

    List<Department> toDepartmentList(List<DepartmentDTO> departmentDTOS);
}
