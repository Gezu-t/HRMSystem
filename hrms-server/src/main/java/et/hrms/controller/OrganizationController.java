package et.hrms.controller;

import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.OrganizationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrganizationController {
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void createEducation(@RequestBody OrganizationDTO organizationDTO,
                         @RequestBody List<AddressDTO> addressDTOS);

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id,
                                                       @RequestBody OrganizationDTO organization) throws Exception;

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OrganizationDTO> getAllOrganization(@RequestParam int page,
                                             @RequestParam int size);
}
