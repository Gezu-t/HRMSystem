package et.hrms.dal.mapping;


import et.hrms.dal.dto.structure.DepartmentDTO;
import et.hrms.dal.model.structure.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);


    @Mapping(target = "id", source = "departmentId")
    @Mapping(target = "branch.id", source = "branchId")
    Department toDepartment(DepartmentDTO departmentDTO);

    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "departmentId", source = "id")
    DepartmentDTO toDepartmentDTO(Department department);

    List<DepartmentDTO> toDepartmentDTOs(List<Department> departments);
}
