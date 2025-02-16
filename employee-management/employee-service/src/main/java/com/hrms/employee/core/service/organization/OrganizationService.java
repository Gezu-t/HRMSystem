package com.hrms.employee.core.service.organization;

import com.hrms.employee.core.dal.dto.organization.OrganizationDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrganizationService {

    void createOrganization(OrganizationDTO organizationDTO);
    OrganizationDTO getOrganizationById(Long id);
    OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO);
    List<OrganizationDTO> getAllOrganization(int page, int size, Sort sort);
    void deleteOrganization(Long id);
    List<OrganizationDTO> getAllOrganizations();
}
