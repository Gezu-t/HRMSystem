package et.hrms.service;

import et.hrms.dal.dto.OrganizationDTO;

import java.util.List;

public interface OrganizationService {

    void createOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO getOrganizationById(Long id);


    OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO);

    List<OrganizationDTO> getAllOrganization(int page, int size);
}
