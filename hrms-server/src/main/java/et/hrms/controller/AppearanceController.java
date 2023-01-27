package et.hrms.controller;

import et.hrms.dal.dto.AppearanceDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AppearanceController {

    void createAppearance(@Valid @PathVariable Long employeeId,
                          @RequestBody AppearanceDTO appearanceDTO);


    AppearanceDTO updateAppearance(@Valid @PathVariable long employeeId,
                                   @RequestBody AppearanceDTO appearanceDTO);

    AppearanceDTO getAppearanceInformation(@PathVariable long appearanceId);

    List<AppearanceDTO> getAllAppearanceList(@RequestParam("page") int page,
                                             @RequestParam("size") int size,
                                             @RequestParam("sort") Sort sort);
}
