package com.hrmsystem.employeeservice.core.dal.mapping.common;

import dal.dto.common.AddressDTO;
import dal.model.branch.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDTO.getId() );
        address.setTelNumberHome( addressDTO.getTelNumberHome() );
        address.setTelNumberOffice( addressDTO.getTelNumberOffice() );
        address.setMobile( addressDTO.getMobile() );
        address.setHouseNumber( addressDTO.getHouseNumber() );
        address.setStreet( addressDTO.getStreet() );
        address.setStreetNumber( addressDTO.getStreetNumber() );
        address.setBuilding( addressDTO.getBuilding() );
        address.setFloor( addressDTO.getFloor() );
        address.setFlat( addressDTO.getFlat() );
        address.setRegion( addressDTO.getRegion() );
        address.setProvince( addressDTO.getProvince() );
        address.setCity( addressDTO.getCity() );
        address.setPostalCode( addressDTO.getPostalCode() );
        address.setCountry( addressDTO.getCountry() );

        return address;
    }

    @Override
    public AddressDTO toAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId( address.getId() );
        addressDTO.setTelNumberHome( address.getTelNumberHome() );
        addressDTO.setTelNumberOffice( address.getTelNumberOffice() );
        addressDTO.setMobile( address.getMobile() );
        addressDTO.setHouseNumber( address.getHouseNumber() );
        addressDTO.setStreet( address.getStreet() );
        addressDTO.setStreetNumber( address.getStreetNumber() );
        addressDTO.setBuilding( address.getBuilding() );
        addressDTO.setFloor( address.getFloor() );
        addressDTO.setFlat( address.getFlat() );
        addressDTO.setRegion( address.getRegion() );
        addressDTO.setProvince( address.getProvince() );
        addressDTO.setCity( address.getCity() );
        addressDTO.setPostalCode( address.getPostalCode() );
        addressDTO.setCountry( address.getCountry() );

        return addressDTO;
    }

    @Override
    public void updateAddress(AddressDTO addressDTO, Address address) {
        if ( addressDTO == null ) {
            return;
        }

        address.setId( addressDTO.getId() );
        address.setTelNumberHome( addressDTO.getTelNumberHome() );
        address.setTelNumberOffice( addressDTO.getTelNumberOffice() );
        address.setMobile( addressDTO.getMobile() );
        address.setHouseNumber( addressDTO.getHouseNumber() );
        address.setStreet( addressDTO.getStreet() );
        address.setStreetNumber( addressDTO.getStreetNumber() );
        address.setBuilding( addressDTO.getBuilding() );
        address.setFloor( addressDTO.getFloor() );
        address.setFlat( addressDTO.getFlat() );
        address.setRegion( addressDTO.getRegion() );
        address.setProvince( addressDTO.getProvince() );
        address.setCity( addressDTO.getCity() );
        address.setPostalCode( addressDTO.getPostalCode() );
        address.setCountry( addressDTO.getCountry() );
    }
}
