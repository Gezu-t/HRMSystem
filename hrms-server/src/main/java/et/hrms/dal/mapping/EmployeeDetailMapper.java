package et.hrms.dal.mapping;

import et.hrms.dal.dto.EmployeeDetailDTO;
import et.hrms.dal.model.EmployeeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeDetailMapper {

    EmployeeDetailMapper INSTANCE = Mappers.getMapper(EmployeeDetailMapper.class);

    @Mapping(target = "department", source = "departmentDTO")
    @Mapping(target = "id", source = "employeeDetailId")
    @Mapping(target = "employee", source = "employeeDTO")
    EmployeeDetail toEmployeeDetail(EmployeeDetailDTO employeeDetailDTO);


    @Mapping(target = "departmentDTO", source = "department")
    @Mapping(target = "employeeDetailId", source = "id")
    @Mapping(target = "employeeDTO", source = "employee")
    EmployeeDetailDTO toEmployeeDetailDTO(EmployeeDetail employeeDetail);


    List<EmployeeDetailDTO> toEmployeeDetailDTOs (List<EmployeeDetail> employeeDetails);
}
