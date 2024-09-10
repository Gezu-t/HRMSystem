package organization;

import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationAddressDTO;
import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationDTO;
import com.hrmsystem.employeeprofileservice.dal.mapping.organization.OrganizationAddressMapper;
import com.hrmsystem.employeeprofileservice.dal.mapping.organization.OrganizationMapper;
import com.hrmsystem.employeeprofileservice.service.log.AuditService;
import com.hrmsystem.employeeprofileservice.service.log.LogService;
import com.hrmsystem.employeeprofileservice.service.organization.impl.OrganizationServiceImpl;
import dal.model.organization.Organization;
import dal.model.organization.OrganizationAddress;
import dal.repository.organization.OrganizationAddressRepository;
import dal.repository.organization.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationMapper organizationMapper;

    @Mock
    private OrganizationAddressMapper organizationAddressMapper;

    @Mock
    private OrganizationAddressRepository organizationAddressRepository;

    @Mock
    private AuditService auditService;

    @Mock
    private LogService logService;

    private OrganizationDTO organizationDTO;
    private OrganizationAddressDTO organizationAddressDTO;
    private Organization organization;
    private OrganizationAddress organizationAddress;

    @BeforeEach
    void setUp() {
        organizationDTO = new OrganizationDTO();
        organizationDTO.setId(1L);
        organizationDTO.setOrganizationName("Organization");
        organizationDTO.setEstablishmentDate(LocalDate.now());
        organizationDTO.setOwnerName("Owner");

        organizationAddressDTO = new OrganizationAddressDTO();
        organizationAddressDTO.setId(1L);
        organizationAddressDTO.setTelNumberHome("123456789");
        organizationAddressDTO.setTelNumberOffice("987654321");
        organizationAddressDTO.setMobile("123456789");

        organizationDTO.setOrganizationAddressDTO(organizationAddressDTO);

        organization = new Organization();
        organization.setId(1L);
        organization.setOrganizationName("Organization");
        organization.setEstablishmentDate(LocalDate.now());
        organization.setOwnerName("Owner");

        organizationAddress = new OrganizationAddress();
        organizationAddress.setId(1L);
        organizationAddress.setTelNumberHome("123456789");
        organizationAddress.setTelNumberOffice("987654321");
        organizationAddress.setMobile("123456789");

        organization.setOrganizationAddress(organizationAddress);
        organizationAddress.setOrganization(organization);
    }

    @Test
    void createOrganization() {
        when(organizationMapper.toOrganization(any(OrganizationDTO.class))).thenReturn(organization);
        when(organizationAddressMapper.toOrganizationAddress(any(OrganizationAddressDTO.class))).thenReturn(organizationAddress);
        when(organizationRepository.save(any(Organization.class))).thenReturn(organization);

        organizationService.createOrganization(organizationDTO);

        verify(organizationMapper).toOrganization(any(OrganizationDTO.class));
        verify(organizationAddressMapper).toOrganizationAddress(any(OrganizationAddressDTO.class));
        verify(organizationRepository).save(any(Organization.class));
        verify(auditService, times(1)).logAction(any(), any(), any(), any());
    }

    @Test
    void getOrganizationById() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(organizationMapper.toOrganizationDTO(any(Organization.class))).thenReturn(organizationDTO);

        OrganizationDTO result = organizationService.getOrganizationById(1L);

        assertNotNull(result);
        assertEquals(organizationDTO.getOrganizationName(), result.getOrganizationName());
        verify(organizationRepository).findById(anyLong());
        verify(organizationMapper).toOrganizationDTO(any(Organization.class));
    }

    @Test
    void updateOrganization() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(organizationRepository.save(any(Organization.class))).thenReturn(organization);
        when(organizationMapper.toOrganizationDTO(any(Organization.class))).thenReturn(organizationDTO);

        OrganizationDTO result = organizationService.updateOrganization(1L, organizationDTO);

        assertNotNull(result);
        assertEquals(organizationDTO.getOrganizationName(), result.getOrganizationName());
        verify(organizationRepository).findById(anyLong());
        verify(organizationRepository).save(any(Organization.class));
        verify(auditService).logAction(any(), any(), any(), any());
    }

    @Test
    void getAllOrganization() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<Organization> organizationPage = new PageImpl<>(List.of(organization), pageable, 1);

        when(organizationRepository.findAll(any(Pageable.class))).thenReturn(organizationPage);
        when(organizationMapper.toOrganizationDTO(any(Organization.class))).thenReturn(organizationDTO);

        List<OrganizationDTO> result = organizationService.getAllOrganization(0, 10, Sort.by("id"));

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(organizationDTO.getOrganizationName(), result.get(0).getOrganizationName());
        verify(organizationRepository).findAll(any(Pageable.class));
        verify(organizationMapper).toOrganizationDTO(any(Organization.class));
        verify(logService).log(any());
    }

    @Test
    void deleteOrganization() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));

        organizationService.deleteOrganization(1L);

        verify(organizationRepository).findById(anyLong());
        verify(organizationRepository).delete(any(Organization.class));
        verify(auditService).logAction(any(), any(), any(), any());
    }
}
