package et.hrms.service;

import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.OrganizationDTO;

public interface OrganizationAddressManagementService {
    void createOrganizationAddressManagement(OrganizationDTO organizationDTO, AddressDTO addressDTO);
}
