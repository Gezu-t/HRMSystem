package com.hrmsystem.employeeprofileservice.dal.mapping;

import com.hrmsystem.employeeprofileservice.dal.dto.EmployeeProfileDTO;
import com.hrmsystem.employeeprofileservice.dal.model.EmployeeProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:40+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeeProfileMapperImpl implements EmployeeProfileMapper {

    @Override
    public EmployeeProfileDTO toEmployeeProfileDTO(EmployeeProfile employeeProfile) {
        if ( employeeProfile == null ) {
            return null;
        }

        EmployeeProfileDTO employeeProfileDTO = new EmployeeProfileDTO();

        employeeProfileDTO.setEmployeeId( employeeProfile.getEmployeeId() );
        employeeProfileDTO.setAddress( employeeProfile.getAddress() );
        employeeProfileDTO.setPhoneNumber( employeeProfile.getPhoneNumber() );
        employeeProfileDTO.setEmergencyContact( employeeProfile.getEmergencyContact() );
        employeeProfileDTO.setDepartment( employeeProfile.getDepartment() );
        employeeProfileDTO.setPosition( employeeProfile.getPosition() );
        employeeProfileDTO.setManagerId( employeeProfile.getManagerId() );
        employeeProfileDTO.setEducationLevel( employeeProfile.getEducationLevel() );
        employeeProfileDTO.setSkills( employeeProfile.getSkills() );

        return employeeProfileDTO;
    }

    @Override
    public EmployeeProfile toEmployeeProfile(EmployeeProfileDTO employeeProfileDTO) {
        if ( employeeProfileDTO == null ) {
            return null;
        }

        EmployeeProfile employeeProfile = new EmployeeProfile();

        employeeProfile.setEmployeeId( employeeProfileDTO.getEmployeeId() );
        employeeProfile.setAddress( employeeProfileDTO.getAddress() );
        employeeProfile.setPhoneNumber( employeeProfileDTO.getPhoneNumber() );
        employeeProfile.setEmergencyContact( employeeProfileDTO.getEmergencyContact() );
        employeeProfile.setDepartment( employeeProfileDTO.getDepartment() );
        employeeProfile.setPosition( employeeProfileDTO.getPosition() );
        employeeProfile.setManagerId( employeeProfileDTO.getManagerId() );
        employeeProfile.setEducationLevel( employeeProfileDTO.getEducationLevel() );
        employeeProfile.setSkills( employeeProfileDTO.getSkills() );

        return employeeProfile;
    }
}
