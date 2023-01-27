package et.hrms.controller.impl;

import et.hrms.controller.AppearanceController;
import et.hrms.dal.dto.AppearanceDTO;
import et.hrms.service.AppearanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/appearance")
@RequiredArgsConstructor
public class AppearanceControllerImpl implements AppearanceController {

    private final AppearanceService appearanceService;


    @Override
    @PostMapping(path = "/add/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAppearance(@Valid @PathVariable Long employeeId,
                                 @RequestBody AppearanceDTO appearanceDTO) {
        appearanceService.createEmployeeAppearance(employeeId, appearanceDTO);
    }

    @Override
    @PutMapping(path = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AppearanceDTO updateAppearance(@Valid @PathVariable long employeeId,
                                          @RequestBody AppearanceDTO appearanceDTO) {
        return appearanceService.updateAppearance(employeeId, appearanceDTO);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{appearanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppearanceDTO getAppearanceInformation(@PathVariable long appearanceId) {
        return appearanceService.getAppearanceById(appearanceId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = {"page", "size", "sort"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppearanceDTO> getAllAppearanceList(@RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam(value = "sort", required = false) Sort sort) {
        return appearanceService.getAllAppearanceLists(page, size, sort);
    }
}
