package et.hrms.dal.mapping;


import et.hrms.dal.dto.employee.EmployeePromotionDTO;
import et.hrms.dal.model.employee.EmployeePromotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EmployeePromotionMapper {

    EmployeePromotionMapper INSTANCE = Mappers.getMapper(EmployeePromotionMapper.class);

    EmployeePromotion toEmployeePromotion(EmployeePromotionDTO employeePromotionDTO);

    EmployeePromotionDTO toEmployeePromotionDTO(EmployeePromotion employeePromotion);



}
