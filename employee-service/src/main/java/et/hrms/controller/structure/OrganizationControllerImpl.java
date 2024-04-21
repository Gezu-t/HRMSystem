package et.hrms.controller.structure;


import et.hrms.dal.dto.structure.OrganizationDTO;
import et.hrms.service.structure.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationControllerImpl implements OrganizationController {

    private final OrganizationService organizationService;

    @Override
    @PostMapping
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
