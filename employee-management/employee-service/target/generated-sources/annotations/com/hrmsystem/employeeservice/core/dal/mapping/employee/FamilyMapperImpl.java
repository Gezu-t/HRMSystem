package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.FamilyDTO;
import dal.model.employee.Employee;
import dal.model.employee.Family;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class FamilyMapperImpl implements FamilyMapper {

    @Override
    public FamilyDTO toFamilyDTO(Family family) {
        if ( family == null ) {
            return null;
        }

        FamilyDTO familyDTO = new FamilyDTO();

        familyDTO.setId( family.getId() );
        familyDTO.setEmployeeId( familyEmployeeId( family ) );
        familyDTO.setNationality( family.getNationality() );
        familyDTO.setPayGrade( family.getPayGrade() );
        familyDTO.setDateBirth( family.getDateBirth() );
        familyDTO.setGender( family.getGender() );
        familyDTO.setFamilyFirstName( family.getFamilyFirstName() );
        familyDTO.setFamilyLastName( family.getFamilyLastName() );
        familyDTO.setEmergencyContact( family.getEmergencyContact() );

        return familyDTO;
    }

    @Override
    public Family toFamily(FamilyDTO familyDTO) {
        if ( familyDTO == null ) {
            return null;
        }

        Family family = new Family();

        family.setEmployee( familyDTOToEmployee( familyDTO ) );
        family.setId( familyDTO.getId() );
        family.setNationality( familyDTO.getNationality() );
        family.setPayGrade( familyDTO.getPayGrade() );
        family.setDateBirth( familyDTO.getDateBirth() );
        family.setGender( familyDTO.getGender() );
        family.setFamilyFirstName( familyDTO.getFamilyFirstName() );
        family.setFamilyLastName( familyDTO.getFamilyLastName() );
        family.setEmergencyContact( familyDTO.getEmergencyContact() );

        return family;
    }

    private Long familyEmployeeId(Family family) {
        if ( family == null ) {
            return null;
        }
        Employee employee = family.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Employee familyDTOToEmployee(FamilyDTO familyDTO) {
        if ( familyDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( familyDTO.getEmployeeId() );

        return employee;
    }
}
