package com.gtltek.employee.core.service.employee;

import com.gtltek.employee.core.dal.dto.employee.EmployeeAppearanceDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AppearanceService {

    void createEmployeeAppearance(Long employeeId, EmployeeAppearanceDTO employeeAppearanceDTO);

    EmployeeAppearanceDTO getAppearanceById(long id);

    EmployeeAppearanceDTO updateAppearance(long appearanceId, EmployeeAppearanceDTO employeeAppearanceDTO);

    List<EmployeeAppearanceDTO> getAllAppearanceLists(int page, int size, Sort sort);
}
