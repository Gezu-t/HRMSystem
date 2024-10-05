package com.hrmsystem.employeeservice.core.dal.mapping.organization;

import dal.dto.organization.OwnersDTO;
import dal.model.organization.Owners;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class OwnersMapperImpl implements OwnersMapper {

    @Override
    public OwnersDTO toOwnersDTO(Owners owners) {
        if ( owners == null ) {
            return null;
        }

        OwnersDTO ownersDTO = new OwnersDTO();

        ownersDTO.setId( owners.getId() );
        ownersDTO.setFirstName( owners.getFirstName() );
        ownersDTO.setLastName( owners.getLastName() );
        ownersDTO.setBirthPlace( owners.getBirthPlace() );
        if ( owners.getBirthDate() != null ) {
            ownersDTO.setBirthDate( LocalDate.parse( owners.getBirthDate() ) );
        }
        ownersDTO.setNationality( owners.getNationality() );
        ownersDTO.setIdNumber( owners.getIdNumber() );
        ownersDTO.setRole( owners.getRole() );
        ownersDTO.setEmail( owners.getEmail() );
        ownersDTO.setPhoneNumber( owners.getPhoneNumber() );

        return ownersDTO;
    }

    @Override
    public Owners toOwners(OwnersDTO ownersDTO) {
        if ( ownersDTO == null ) {
            return null;
        }

        Owners owners = new Owners();

        owners.setId( ownersDTO.getId() );
        owners.setFirstName( ownersDTO.getFirstName() );
        owners.setLastName( ownersDTO.getLastName() );
        if ( ownersDTO.getBirthDate() != null ) {
            owners.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( ownersDTO.getBirthDate() ) );
        }
        owners.setBirthPlace( ownersDTO.getBirthPlace() );
        owners.setNationality( ownersDTO.getNationality() );
        owners.setIdNumber( ownersDTO.getIdNumber() );
        owners.setRole( ownersDTO.getRole() );
        owners.setEmail( ownersDTO.getEmail() );
        owners.setPhoneNumber( ownersDTO.getPhoneNumber() );

        return owners;
    }

    @Override
    public void updateOwners(OwnersDTO ownersDTO, Owners owners) {
        if ( ownersDTO == null ) {
            return;
        }

        owners.setId( ownersDTO.getId() );
        owners.setFirstName( ownersDTO.getFirstName() );
        owners.setLastName( ownersDTO.getLastName() );
        if ( ownersDTO.getBirthDate() != null ) {
            owners.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( ownersDTO.getBirthDate() ) );
        }
        else {
            owners.setBirthDate( null );
        }
        owners.setBirthPlace( ownersDTO.getBirthPlace() );
        owners.setNationality( ownersDTO.getNationality() );
        owners.setIdNumber( ownersDTO.getIdNumber() );
        owners.setRole( ownersDTO.getRole() );
        owners.setEmail( ownersDTO.getEmail() );
        owners.setPhoneNumber( ownersDTO.getPhoneNumber() );
    }

    @Override
    public List<OwnersDTO> toOwnersDTOList(List<Owners> owners) {
        if ( owners == null ) {
            return null;
        }

        List<OwnersDTO> list = new ArrayList<OwnersDTO>( owners.size() );
        for ( Owners owners1 : owners ) {
            list.add( toOwnersDTO( owners1 ) );
        }

        return list;
    }
}
