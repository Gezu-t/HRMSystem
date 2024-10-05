package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeePositionDTO;
import dal.dto.employee.EmployeePositionManagementDTO;
import dal.model.employee.EmployeePosition;
import dal.model.employee.EmployeePositionManagement;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeePositionMapperImpl implements EmployeePositionMapper {

    @Override
    public EmployeePosition toEmployeePosition(EmployeePositionDTO employeePositionDTO) {
        if ( employeePositionDTO == null ) {
            return null;
        }

        EmployeePosition employeePosition = new EmployeePosition();

        employeePosition.setId( employeePositionDTO.getId() );
        employeePosition.setJobTitle( employeePositionDTO.getJobTitle() );
        employeePosition.setJobLevel( employeePositionDTO.getJobLevel() );
        employeePosition.setStartDate( employeePositionDTO.getStartDate() );
        employeePosition.setEmployeePositionManagements( employeePositionManagementDTOSetToEmployeePositionManagementSet( employeePositionDTO.getEmployeePositionManagements() ) );

        return employeePosition;
    }

    @Override
    public EmployeePositionDTO toEmployeePositionDTO(EmployeePosition employeePosition) {
        if ( employeePosition == null ) {
            return null;
        }

        EmployeePositionDTO employeePositionDTO = new EmployeePositionDTO();

        employeePositionDTO.setId( employeePosition.getId() );
        employeePositionDTO.setJobTitle( employeePosition.getJobTitle() );
        employeePositionDTO.setJobLevel( employeePosition.getJobLevel() );
        employeePositionDTO.setStartDate( employeePosition.getStartDate() );
        employeePositionDTO.setEmployeePositionManagements( employeePositionManagementSetToEmployeePositionManagementDTOSet( employeePosition.getEmployeePositionManagements() ) );

        return employeePositionDTO;
    }

    protected EmployeePositionManagement employeePositionManagementDTOToEmployeePositionManagement(EmployeePositionManagementDTO employeePositionManagementDTO) {
        if ( employeePositionManagementDTO == null ) {
            return null;
        }

        EmployeePositionManagement employeePositionManagement = new EmployeePositionManagement();

        employeePositionManagement.setId( employeePositionManagementDTO.getId() );
        employeePositionManagement.setCreatedAt( employeePositionManagementDTO.getCreatedAt() );
        employeePositionManagement.setUpdatedAt( employeePositionManagementDTO.getUpdatedAt() );
        employeePositionManagement.setCreationStatus( employeePositionManagementDTO.getCreationStatus() );
        employeePositionManagement.setUpdateStatus( employeePositionManagementDTO.getUpdateStatus() );

        return employeePositionManagement;
    }

    protected Set<EmployeePositionManagement> employeePositionManagementDTOSetToEmployeePositionManagementSet(Set<EmployeePositionManagementDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<EmployeePositionManagement> set1 = new LinkedHashSet<EmployeePositionManagement>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EmployeePositionManagementDTO employeePositionManagementDTO : set ) {
            set1.add( employeePositionManagementDTOToEmployeePositionManagement( employeePositionManagementDTO ) );
        }

        return set1;
    }

    protected EmployeePositionManagementDTO employeePositionManagementToEmployeePositionManagementDTO(EmployeePositionManagement employeePositionManagement) {
        if ( employeePositionManagement == null ) {
            return null;
        }

        EmployeePositionManagementDTO employeePositionManagementDTO = new EmployeePositionManagementDTO();

        employeePositionManagementDTO.setId( employeePositionManagement.getId() );
        employeePositionManagementDTO.setCreatedAt( employeePositionManagement.getCreatedAt() );
        employeePositionManagementDTO.setUpdatedAt( employeePositionManagement.getUpdatedAt() );
        employeePositionManagementDTO.setCreationStatus( employeePositionManagement.getCreationStatus() );
        employeePositionManagementDTO.setUpdateStatus( employeePositionManagement.getUpdateStatus() );

        return employeePositionManagementDTO;
    }

    protected Set<EmployeePositionManagementDTO> employeePositionManagementSetToEmployeePositionManagementDTOSet(Set<EmployeePositionManagement> set) {
        if ( set == null ) {
            return null;
        }

        Set<EmployeePositionManagementDTO> set1 = new LinkedHashSet<EmployeePositionManagementDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EmployeePositionManagement employeePositionManagement : set ) {
            set1.add( employeePositionManagementToEmployeePositionManagementDTO( employeePositionManagement ) );
        }

        return set1;
    }
}
