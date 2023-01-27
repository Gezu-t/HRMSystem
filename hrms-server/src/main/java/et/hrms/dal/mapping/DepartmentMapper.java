package et.hrms.dal.mapping;


import et.hrms.dal.dto.DepartmentDTO;
import et.hrms.dal.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);


    @Mapping(target = "id", source = "departmentId")
    @Mapping(target = "branch.id", source = "branchId")
    Department toDepartment(DepartmentDTO departmentDTO);

    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "departmentId", source = "id")
    DepartmentDTO toDepartmentDTO(Department department);

    Set<Department> toDepartmentDTOs(Set<DepartmentDTO> departmentDTOS);
    List<DepartmentDTO> toDepartmentList(List<Department> departments);
    Set<DepartmentDTO> toDepartmentSet(Set<Department> departments);

    List<Department> toDepartmentDTOs(List<DepartmentDTO> departments);
}
