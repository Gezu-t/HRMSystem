package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import dal.dto.employee.EmployeePromotionDTO;
import dal.model.employee.EmployeePromotion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeePromotionMapperImpl implements EmployeePromotionMapper {

    @Override
    public EmployeePromotion toEmployeePromotion(EmployeePromotionDTO employeePromotionDTO) {
        if ( employeePromotionDTO == null ) {
            return null;
        }

        EmployeePromotion employeePromotion = new EmployeePromotion();

        employeePromotion.setId( employeePromotionDTO.getId() );
        employeePromotion.setDateOfLastPromotion( employeePromotionDTO.getDateOfLastPromotion() );
        employeePromotion.setDateOfLastIncrement( employeePromotionDTO.getDateOfLastIncrement() );
        employeePromotion.setDateOfLastDecrement( employeePromotionDTO.getDateOfLastDecrement() );
        employeePromotion.setDateOfLastSalaryChange( employeePromotionDTO.getDateOfLastSalaryChange() );
        employeePromotion.setDateOfLastSalaryIncrement( employeePromotionDTO.getDateOfLastSalaryIncrement() );
        employeePromotion.setDateOfLastSalaryDecrement( employeePromotionDTO.getDateOfLastSalaryDecrement() );
        employeePromotion.setDateOfLastSalaryChangeReason( employeePromotionDTO.getDateOfLastSalaryChangeReason() );
        employeePromotion.setPromotionStatus( employeePromotionDTO.getPromotionStatus() );

        return employeePromotion;
    }

    @Override
    public EmployeePromotionDTO toEmployeePromotionDTO(EmployeePromotion employeePromotion) {
        if ( employeePromotion == null ) {
            return null;
        }

        EmployeePromotionDTO employeePromotionDTO = new EmployeePromotionDTO();

        employeePromotionDTO.setId( employeePromotion.getId() );
        employeePromotionDTO.setDateOfLastPromotion( employeePromotion.getDateOfLastPromotion() );
        employeePromotionDTO.setDateOfLastIncrement( employeePromotion.getDateOfLastIncrement() );
        employeePromotionDTO.setDateOfLastDecrement( employeePromotion.getDateOfLastDecrement() );
        employeePromotionDTO.setDateOfLastSalaryChange( employeePromotion.getDateOfLastSalaryChange() );
        employeePromotionDTO.setDateOfLastSalaryIncrement( employeePromotion.getDateOfLastSalaryIncrement() );
        employeePromotionDTO.setDateOfLastSalaryDecrement( employeePromotion.getDateOfLastSalaryDecrement() );
        employeePromotionDTO.setDateOfLastSalaryChangeReason( employeePromotion.getDateOfLastSalaryChangeReason() );
        employeePromotionDTO.setPromotionStatus( employeePromotion.getPromotionStatus() );

        return employeePromotionDTO;
    }
}
