package et.hrms.controller.structure;

import et.hrms.dal.dto.structure.OrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrganizationController {

    void createEducation(@RequestBody OrganizationDTO organizationDTO);
    ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id,
                                                       @RequestBody OrganizationDTO organization);
    List<OrganizationDTO> getAllOrganization(@RequestParam int page,
                                             @RequestParam int size);
}
