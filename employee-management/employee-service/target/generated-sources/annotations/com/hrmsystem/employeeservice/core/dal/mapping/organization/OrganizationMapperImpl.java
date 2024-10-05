package com.hrmsystem.employeeservice.core.dal.mapping.organization;

import dal.dto.organization.OrganizationDTO;
import dal.dto.organization.OwnersDTO;
import dal.model.organization.Organization;
import dal.model.organization.Owners;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T14:35:08+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    @Autowired
    private OwnersMapper ownersMapper;

    @Override
    public OrganizationDTO toOrganizationDTO(Organization entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( entity.getId() );
        organizationDTO.setOrganizationName( entity.getOrganizationName() );
        organizationDTO.setOrganizationCode( entity.getOrganizationCode() );
        organizationDTO.setEstablishmentDate( entity.getEstablishmentDate() );
        organizationDTO.setOwners( ownersMapper.toOwnersDTOList( entity.getOwners() ) );

        return organizationDTO;
    }

    @Override
    public Organization toOrganization(OrganizationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Organization organization = new Organization();

        organization.setId( dto.getId() );
        organization.setOrganizationName( dto.getOrganizationName() );
        organization.setOrganizationCode( dto.getOrganizationCode() );
        organization.setEstablishmentDate( dto.getEstablishmentDate() );
        organization.setOwners( ownersDTOListToOwnersList( dto.getOwners() ) );

        return organization;
    }

    @Override
    public void updateOrganization(OrganizationDTO dto, Organization entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setOrganizationName( dto.getOrganizationName() );
        entity.setOrganizationCode( dto.getOrganizationCode() );
        entity.setEstablishmentDate( dto.getEstablishmentDate() );
        if ( entity.getOwners() != null ) {
            List<Owners> list = ownersDTOListToOwnersList( dto.getOwners() );
            if ( list != null ) {
                entity.getOwners().clear();
                entity.getOwners().addAll( list );
            }
            else {
                entity.setOwners( null );
            }
        }
        else {
            List<Owners> list = ownersDTOListToOwnersList( dto.getOwners() );
            if ( list != null ) {
                entity.setOwners( list );
            }
        }
    }

    protected List<Owners> ownersDTOListToOwnersList(List<OwnersDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Owners> list1 = new ArrayList<Owners>( list.size() );
        for ( OwnersDTO ownersDTO : list ) {
            list1.add( ownersMapper.toOwners( ownersDTO ) );
        }

        return list1;
    }
}
