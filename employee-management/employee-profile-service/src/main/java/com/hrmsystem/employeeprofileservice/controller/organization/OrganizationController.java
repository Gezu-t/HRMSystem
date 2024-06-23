package com.hrmsystem.employeeprofileservice.controller.organization;

import com.hrmsystem.employeeprofileservice.dal.dto.department.OrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrganizationController {

    ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO);

    ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDTO organization);

    ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id);


//    ResponseEntity<List<OrganizationDTO>> getAllOrganization(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "sort", defaultValue = "id,asc") String sort);

    @GetMapping
    ResponseEntity<List<OrganizationDTO>> getAllOrganizations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort);

    ResponseEntity<Void> deleteOrganization(@PathVariable Long id);

    @GetMapping("/all")
    ResponseEntity<List<OrganizationDTO>> getAllOrganization();
}
