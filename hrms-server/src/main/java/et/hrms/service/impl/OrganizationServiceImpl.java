package et.hrms.service.impl;

import et.hrms.dal.dto.AddressDTO;
import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.mapping.AddressMapper;
import et.hrms.dal.mapping.OrganizationMapper;
import et.hrms.dal.model.Address;
import et.hrms.dal.model.Organization;
import et.hrms.dal.model.OrganizationAddressManagement;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.service.OrganizationAddressManagementService;
import et.hrms.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    private final AddressMapper addressMapper;

    private final OrganizationAddressManagementService organizationAddressManagementService;


    @Override
    @Transactional
    public void createOrganization(OrganizationDTO organizationDTO, List<AddressDTO> addressDTOS) {
        Organization organization = organizationMapper.toOrganization(organizationDTO);
        Set<OrganizationAddressManagement> organizationAddressManagements = new HashSet<>();
        OrganizationAddressManagement organizationAddressManagement = new OrganizationAddressManagement();
        for (AddressDTO addressDTO : addressDTOS) {
            Address address = addressMapper.toAddress(addressDTO);
            organizationAddressManagement.setOrganization(organization);
            organizationAddressManagement.setAddress(address);
            organizationAddressManagements.add(organizationAddressManagement);
        }
        organization.setOrganizationAddressManagements(organizationAddressManagements);
        organizationRepository.save(organization);
    }


    @Override
    public OrganizationDTO getOrganizationById(Long id)  {
        Organization organization = organizationRepository.findById(id).orElseThrow();

        if (organization != null) {
            return organizationMapper.toOrganizationDTO(organization);
        } else {
            // do something else, or throw a different exception
            return null;
        }

    }


    @Override
    public OrganizationDTO updateOrganization(OrganizationDTO organizationDTO) {
        Organization organization = organizationMapper.toOrganization(organizationDTO);
        organizationRepository.save(organization);

        return organizationMapper.toOrganizationDTO(organization);
    }

    @Override
    public List<OrganizationDTO> getAllOrganization(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Organization> organizations = organizationRepository.findAll(pageable);
        return organizations.stream()
                .map(organizationMapper::toOrganizationDTO)
                .toList();
    }

}
