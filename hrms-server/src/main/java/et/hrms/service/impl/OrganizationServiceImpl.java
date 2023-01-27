package et.hrms.service.impl;

import et.hrms.dal.dto.OrganizationDTO;
import et.hrms.dal.mapping.OrganizationAddressMapper;
import et.hrms.dal.mapping.OrganizationMapper;
import et.hrms.dal.model.Organization;
import et.hrms.dal.model.OrganizationAddress;
import et.hrms.dal.repository.OrganizationAddressRepository;
import et.hrms.dal.repository.OrganizationRepository;
import et.hrms.exceptions.EntityNotFoundException;
import et.hrms.service.AuditService;
import et.hrms.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    private final OrganizationAddressMapper organizationAddressMapper;


    private final OrganizationAddressRepository organizationAddressRepository;

    private final AuditService auditService;


    @Override
    @Transactional
    public void createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = OrganizationMapper.INSTANCE.toOrganization(organizationDTO);
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
        Organization org = organizationRepository.save(organization);

        log.debug("Saved organization object: {}", org);
        auditService.logAction("username", "Organization", "Create", org.getId());

    }


    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found by this id:" + id));

        if (organization != null) {
            return organizationMapper.toOrganizationDTO(organization);
        } else {
            // do something else, or throw a different exception
            throw new EntityNotFoundException("Organization information is not found by this id:" + id);
        }

    }


    @Override
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization existingOrganization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization is not found by this id:" + id));
        Long addressId = existingOrganization.getOrganizationAddress().getId();

        OrganizationAddress existingOrganizationAddress = organizationAddressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("OrganizationAddress not found by this id: " + addressId));

        if (existingOrganizationAddress != null) {
            existingOrganization = organizationMapper.toOrganization(organizationDTO);
            existingOrganization.setId(id);

            // update with new addresses
            existingOrganizationAddress = organizationAddressMapper.toOrganizationAddress(organizationDTO.getOrganizationAddressDTO());
            existingOrganizationAddress.setId(addressId);

            existingOrganizationAddress.setOrganization(existingOrganization);

            existingOrganization.setOrganizationAddress(existingOrganizationAddress);
            auditService.logAction("username", "Organization", "Update", existingOrganization.getId());
            return organizationMapper.toOrganizationDTO(organizationRepository.save(existingOrganization));
        } else {
            throw new EntityNotFoundException("Organization information is not found");
        }
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
