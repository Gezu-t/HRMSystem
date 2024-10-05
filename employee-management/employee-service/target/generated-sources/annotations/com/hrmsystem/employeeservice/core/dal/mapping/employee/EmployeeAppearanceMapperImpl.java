package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeeAppearanceDTO;
import dal.model.employee.Employee;
import dal.model.employee.EmployeeAppearance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeeAppearanceMapperImpl implements EmployeeAppearanceMapper {

    @Override
    public EmployeeAppearance toAppearance(EmployeeAppearanceDTO employeeAppearanceDTO) {
        if ( employeeAppearanceDTO == null ) {
            return null;
        }

        EmployeeAppearance employeeAppearance = new EmployeeAppearance();

        employeeAppearance.setEmployee( employeeAppearanceDTOToEmployee( employeeAppearanceDTO ) );
        employeeAppearance.setId( employeeAppearanceDTO.getId() );
        employeeAppearance.setHeight( employeeAppearanceDTO.getHeight() );
        employeeAppearance.setWeight( employeeAppearanceDTO.getWeight() );
        employeeAppearance.setHairColor( employeeAppearanceDTO.getHairColor() );
        employeeAppearance.setEyeColor( employeeAppearanceDTO.getEyeColor() );
        employeeAppearance.setSkinColor( employeeAppearanceDTO.getSkinColor() );
        employeeAppearance.setBloodGroup( employeeAppearanceDTO.getBloodGroup() );
        employeeAppearance.setChest( employeeAppearanceDTO.getChest() );

        return employeeAppearance;
    }

    @Override
    public EmployeeAppearanceDTO toAppearanceDTO(EmployeeAppearance employeeAppearance) {
        if ( employeeAppearance == null ) {
            return null;
        }

        EmployeeAppearanceDTO employeeAppearanceDTO = new EmployeeAppearanceDTO();

        employeeAppearanceDTO.setEmployeeId( employeeAppearanceEmployeeId( employeeAppearance ) );
        employeeAppearanceDTO.setId( employeeAppearance.getId() );
        employeeAppearanceDTO.setHeight( employeeAppearance.getHeight() );
        employeeAppearanceDTO.setWeight( employeeAppearance.getWeight() );
        employeeAppearanceDTO.setHairColor( employeeAppearance.getHairColor() );
        employeeAppearanceDTO.setEyeColor( employeeAppearance.getEyeColor() );
        employeeAppearanceDTO.setSkinColor( employeeAppearance.getSkinColor() );
        employeeAppearanceDTO.setBloodGroup( employeeAppearance.getBloodGroup() );
        employeeAppearanceDTO.setChest( employeeAppearance.getChest() );

        return employeeAppearanceDTO;
    }

    protected Employee employeeAppearanceDTOToEmployee(EmployeeAppearanceDTO employeeAppearanceDTO) {
        if ( employeeAppearanceDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeAppearanceDTO.getEmployeeId() );

        return employee;
    }

    private Long employeeAppearanceEmployeeId(EmployeeAppearance employeeAppearance) {
        if ( employeeAppearance == null ) {
            return null;
        }
        Employee employee = employeeAppearance.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
