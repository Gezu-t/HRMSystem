package com.gtltek.employee.core.controller.organization.impl;

import com.gtltek.employee.core.controller.organization.OrganizationController;
import com.gtltek.employee.core.dal.dto.organization.OrganizationDTO;
import com.gtltek.employee.core.service.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationControllerImpl implements OrganizationController {

    private final OrganizationService organizationService;

    @Override
    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        organizationService.createOrganization(organizationDTO);
        return new ResponseEntity<>(organizationDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id) {
        OrganizationDTO organizationDTO = organizationService.getOrganizationById(id);
        return ResponseEntity.ok(organizationDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDTO organizationDTO) {
        OrganizationDTO updatedOrganization = organizationService.updateOrganization(id, organizationDTO);
        return ResponseEntity.ok(updatedOrganization);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        List<OrganizationDTO> organizations = organizationService.getAllOrganization(page, size, Sort.by(sort));
        return ResponseEntity.ok(organizations);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganization() {
        List<OrganizationDTO> organizationDTOS = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizationDTOS);
    }
}
