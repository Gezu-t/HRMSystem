package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeeAddressDTO;
import dal.model.employee.EmployeeAddress;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeeAddressMapperImpl implements EmployeeAddressMapper {

    @Override
    public EmployeeAddress toEmployeeAddress(EmployeeAddressDTO employeeAddressDTO) {
        if ( employeeAddressDTO == null ) {
            return null;
        }

        EmployeeAddress employeeAddress = new EmployeeAddress();

        employeeAddress.setId( employeeAddressDTO.getId() );
        employeeAddress.setTelNumberHome( employeeAddressDTO.getTelNumberHome() );
        employeeAddress.setMobile( employeeAddressDTO.getMobile() );
        employeeAddress.setHouseNumber( employeeAddressDTO.getHouseNumber() );
        employeeAddress.setStreet( employeeAddressDTO.getStreet() );
        employeeAddress.setStreetNumber( employeeAddressDTO.getStreetNumber() );
        employeeAddress.setBuilding( employeeAddressDTO.getBuilding() );
        employeeAddress.setFloor( employeeAddressDTO.getFloor() );
        employeeAddress.setFlat( employeeAddressDTO.getFlat() );
        employeeAddress.setRegion( employeeAddressDTO.getRegion() );
        employeeAddress.setProvince( employeeAddressDTO.getProvince() );
        employeeAddress.setCity( employeeAddressDTO.getCity() );
        employeeAddress.setPostalCode( employeeAddressDTO.getPostalCode() );
        employeeAddress.setCountry( employeeAddressDTO.getCountry() );
        employeeAddress.setAddressType( employeeAddressDTO.getAddressType() );
        employeeAddress.setAddressStatus( employeeAddressDTO.getAddressStatus() );
        employeeAddress.setAddressStatusDate( employeeAddressDTO.getAddressStatusDate() );
        employeeAddress.setAddressStatusDescription( employeeAddressDTO.getAddressStatusDescription() );

        return employeeAddress;
    }

    @Override
    public EmployeeAddressDTO toEmployeeAddressDTO(EmployeeAddress employeeAddress) {
        if ( employeeAddress == null ) {
            return null;
        }

        EmployeeAddressDTO employeeAddressDTO = new EmployeeAddressDTO();

        employeeAddressDTO.setId( employeeAddress.getId() );
        employeeAddressDTO.setTelNumberHome( employeeAddress.getTelNumberHome() );
        employeeAddressDTO.setMobile( employeeAddress.getMobile() );
        employeeAddressDTO.setHouseNumber( employeeAddress.getHouseNumber() );
        employeeAddressDTO.setStreet( employeeAddress.getStreet() );
        employeeAddressDTO.setStreetNumber( employeeAddress.getStreetNumber() );
        employeeAddressDTO.setBuilding( employeeAddress.getBuilding() );
        employeeAddressDTO.setFloor( employeeAddress.getFloor() );
        employeeAddressDTO.setFlat( employeeAddress.getFlat() );
        employeeAddressDTO.setRegion( employeeAddress.getRegion() );
        employeeAddressDTO.setProvince( employeeAddress.getProvince() );
        employeeAddressDTO.setCity( employeeAddress.getCity() );
        employeeAddressDTO.setPostalCode( employeeAddress.getPostalCode() );
        employeeAddressDTO.setCountry( employeeAddress.getCountry() );
        employeeAddressDTO.setAddressType( employeeAddress.getAddressType() );
        employeeAddressDTO.setAddressStatus( employeeAddress.getAddressStatus() );
        employeeAddressDTO.setAddressStatusDate( employeeAddress.getAddressStatusDate() );
        employeeAddressDTO.setAddressStatusDescription( employeeAddress.getAddressStatusDescription() );

        return employeeAddressDTO;
    }
}
