package et.hrms.service.impl;


import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.repository.OrganizationAddressManageRepository;
import et.hrms.service.OrganizationAddressManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationAddressManagementServiceImpl implements OrganizationAddressManagementService {



    private final OrganizationAddressManageRepository organizationAddressManageRepository;


    @Override
    public void createOrganizationAddressManagement(OrganizationDTO organizationDTO, AddressDTO addressDTO){

    }
}
