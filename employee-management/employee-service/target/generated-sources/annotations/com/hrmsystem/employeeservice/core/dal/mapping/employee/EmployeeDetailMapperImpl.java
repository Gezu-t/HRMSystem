package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeeDetailDTO;
import dal.model.employee.EmployeeDetail;
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
public class EmployeeDetailMapperImpl implements EmployeeDetailMapper {

    @Override
    public EmployeeDetail toEmployeeDetail(EmployeeDetailDTO employeeDetailDTO) {
        if ( employeeDetailDTO == null ) {
            return null;
        }

        EmployeeDetail employeeDetail = new EmployeeDetail();

        employeeDetail.setId( employeeDetailDTO.getId() );

        return employeeDetail;
    }

    @Override
    public EmployeeDetailDTO toEmployeeDetailDTO(EmployeeDetail employeeDetail) {
        if ( employeeDetail == null ) {
            return null;
        }

        EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();

        employeeDetailDTO.setId( employeeDetail.getId() );

        return employeeDetailDTO;
    }

    @Override
    public List<EmployeeDetailDTO> toEmployeeDetailDTOs(List<EmployeeDetail> employeeDetails) {
        if ( employeeDetails == null ) {
            return null;
        }

        List<EmployeeDetailDTO> list = new ArrayList<EmployeeDetailDTO>( employeeDetails.size() );
        for ( EmployeeDetail employeeDetail : employeeDetails ) {
            list.add( toEmployeeDetailDTO( employeeDetail ) );
        }

        return list;
    }
}
