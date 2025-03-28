package com.hrmsystem.employeeservice.core.controller.employee.imp;

import com.hrmsystem.employeeservice.core.controller.employee.AppearanceController;
import dal.dto.employee.EmployeeAppearanceDTO;
import com.hrmsystem.employeeservice.core.service.employee.AppearanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/appearances")
@RequiredArgsConstructor
public class AppearanceControllerImpl implements AppearanceController {

    private final AppearanceService appearanceService;


    @Override
    @PostMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAppearance(@Valid @PathVariable Long employeeId,
                                 @RequestBody EmployeeAppearanceDTO employeeAppearanceDTO) {
        appearanceService.createEmployeeAppearance(employeeId, employeeAppearanceDTO);
    }

    @Override
    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmployeeAppearanceDTO updateAppearance(@Valid @PathVariable long employeeId,
                                                  @RequestBody EmployeeAppearanceDTO employeeAppearanceDTO) {
        return appearanceService.updateAppearance(employeeId, employeeAppearanceDTO);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{appearanceId}")
    public EmployeeAppearanceDTO getAppearanceInformation(@PathVariable long appearanceId) {
        return appearanceService.getAppearanceById(appearanceId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = {"page", "size", "sort"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeAppearanceDTO> getAllAppearanceList(@RequestParam("page") int page,
                                                            @RequestParam("size") int size,
                                                            @RequestParam(value = "sort", required = false) Sort sort) {
        return appearanceService.getAllAppearanceLists(page, size, sort);
    }
}
