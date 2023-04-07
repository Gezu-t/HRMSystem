package et.hrms.controller.impl;


import et.hrms.controller.FamilyController;
import et.hrms.dal.dto.FamilyDTO;
import et.hrms.service.FamilyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/families")
public class FamilyControllerImpl implements FamilyController {

    private final FamilyService familyService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{employeeId}")
    public void createFamily(@Valid @PathVariable long employeeId, @RequestBody FamilyDTO familyDTO) {
        familyService.createEmployeeFamily(employeeId, familyDTO);
    }

    @Override
    @GetMapping(value = "/{familyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FamilyDTO getFamilyById(@PathVariable long familyId){
        return familyService.getFamilyById(familyId);
    }




}
