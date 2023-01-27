package et.hrms.controller.impl;


import et.hrms.controller.OrganizationController;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class OrganizationControllerImpl implements OrganizationController {



    private final OrganizationService organizationService;

    @Override
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEducation(@RequestBody OrganizationDTO organizationDTO) {
        organizationService.createOrganization(organizationDTO);

    }


    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id,
                                                              @RequestBody OrganizationDTO organization) {
        if (organizationService.getOrganizationById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        organization.setOrganizationId(id);
        return new ResponseEntity<>(organizationService.updateOrganization(id, organization), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationDTO> getAllOrganization(@RequestParam int page,
                                                    @RequestParam int size) {
        return organizationService.getAllOrganization(page, size);
    }

}
