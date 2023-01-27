package et.hrms.dal.mapping;


import et.hrms.dal.dto.EmployeeAddressDTO;
import et.hrms.dal.model.EmployeeAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeAddressMapper {


    EmployeeAddressMapper INSTANCE = Mappers.getMapper(EmployeeAddressMapper.class);

    @Mapping(target = "id", source = "addressId")
//    @Mapping(target = "employee.id", source = "employeeDTO")
    EmployeeAddress toEmployeeAddress(EmployeeAddressDTO employeeAddressDTO);

//    @Mapping(target = "employeeDTO", source = "employee")
    @Mapping(target = "addressId", source = "id")
    EmployeeAddressDTO toEmployeeAddressDTO(EmployeeAddress employeeAddress);



}
