package et.hrms.service.structure;

import et.hrms.dal.dto.structure.OrganizationDTO;

import java.util.List;

public interface OrganizationService {

    void createOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO getOrganizationById(Long id);


    OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO);

    List<OrganizationDTO> getAllOrganization(int page, int size);
}
