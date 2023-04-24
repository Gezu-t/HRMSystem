package et.hrms.service.structure;

import et.hrms.dal.dto.structure.OrganizationAddressDTO;
import et.hrms.dal.dto.structure.OrganizationDTO;
import et.hrms.dal.mapping.OrganizationAddressMapper;
import et.hrms.dal.mapping.OrganizationMapper;
import et.hrms.dal.model.structure.Organization;
import et.hrms.dal.model.structure.OrganizationAddress;
import et.hrms.dal.repository.structure.OrganizationAddressRepository;
import et.hrms.dal.repository.structure.OrganizationRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.log.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final OrganizationAddressMapper organizationAddressMapper;
    private final OrganizationAddressRepository organizationAddressRepository;
    private final AuditService auditService;

    @Override
    @Transactional
    public void createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = organizationMapper.toOrganization(organizationDTO);
        OrganizationAddress organizationAddress = organizationAddressMapper.toOrganizationAddress(organizationDTO.getOrganizationAddressDTO());

        organizationAddress.setOrganization(organization);
        organization.setOrganizationAddress(organizationAddress);

        if (organization.getId() != null) {
            log.debug("Organization object: {}", organization);

            Optional<Organization> existingOrganization = organizationRepository.findById(organization.getId());
            if (existingOrganization.isPresent()) {
                organization = existingOrganization.get();
            }
        }

        Organization savedOrganization = organizationRepository.save(organization);
        log.info("Saved organization object: {}", savedOrganization);
        auditService.logAction("username", "Organization", "Create", savedOrganization.getId());
    }

    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id:" + id));
        return organizationMapper.toOrganizationDTO(organization);
    }

    @Override
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization is not found by this id:" + id));
        Long addressId = existingOrganization.getOrganizationAddress().getId();

        OrganizationAddress existingOrganizationAddress = organizationAddressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("OrganizationAddress not found by this id: " + addressId));

        existingOrganization = updateExistingOrganization(existingOrganization, organizationDTO);
        existingOrganizationAddress = updateExistingOrganizationAddress(existingOrganizationAddress, organizationDTO.getOrganizationAddressDTO(), existingOrganization);

        auditService.logAction("username", "Organization", "Update", existingOrganization.getId());
        return organizationMapper.toOrganizationDTO(organizationRepository.save(existingOrganization));
    }

    private Organization updateExistingOrganization(Organization existingOrganization, OrganizationDTO organizationDTO) {
        existingOrganization = organizationMapper.toOrganization(organizationDTO);
        existingOrganization.setId(existingOrganization.getId());
        return existingOrganization;
    }

    private OrganizationAddress updateExistingOrganizationAddress(OrganizationAddress existingOrganizationAddress, OrganizationAddressDTO organizationAddressDTO, Organization existingOrganization) {
        existingOrganizationAddress = organizationAddressMapper.toOrganizationAddress(organizationAddressDTO);
        existingOrganizationAddress.setId(existingOrganization.getOrganizationAddress().getId());
        existingOrganizationAddress.setOrganization(existingOrganization);
        existingOrganization.setOrganizationAddress(existingOrganizationAddress);
        return existingOrganizationAddress;
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

