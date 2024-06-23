package com.hrmsystem.employeeprofileservice.controller.employee;

import com.hrmsystem.employeeprofileservice.dal.dto.employee.EmployeeAppearanceDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AppearanceController {

    void createAppearance(@Valid @PathVariable Long employeeId,
                          @RequestBody EmployeeAppearanceDTO employeeAppearanceDTO);


    EmployeeAppearanceDTO updateAppearance(@Valid @PathVariable long employeeId,
                                           @RequestBody EmployeeAppearanceDTO employeeAppearanceDTO);

    EmployeeAppearanceDTO getAppearanceInformation(@PathVariable long appearanceId);

    List<EmployeeAppearanceDTO> getAllAppearanceList(@RequestParam("page") int page,
                                                     @RequestParam("size") int size,
                                                     @RequestParam("sort") Sort sort);
}
