package et.hrms.controller;

import et.hrms.dal.dto.FamilyDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface FamilyController {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    void createFamily(@Valid @PathVariable long employeeId, @RequestBody FamilyDTO familyDTO);

    @GetMapping(value = "/{familyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FamilyDTO getFamilyById(@PathVariable long familyId);
}
