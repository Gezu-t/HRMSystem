package et.hrms.service;

import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.OrganizationDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationService {

    void createOrganization(OrganizationDTO organizationDTO, List<AddressDTO> addressDTOS);

    OrganizationDTO getOrganizationById(Long id) throws Exception;

    OrganizationDTO updateOrganization(OrganizationDTO organizationDTO);

    List<OrganizationDTO> getAllOrganization(int page, int size);
}
