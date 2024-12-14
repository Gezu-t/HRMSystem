package et.hrms.service;

import et.hrms.dal.model.Role;
import et.hrms.dal.repository.RoleRepository;
import et.hrms.exception.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    // Define standard role names as constants
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";

    @Transactional
    @Override
    public void createDefaultRolesIfNotExist() {
        log.debug("Checking and creating default roles if they don't exist");

        createRoleIfNotExists(ROLE_USER);
        createRoleIfNotExists(ROLE_ADMIN);
        createRoleIfNotExists(ROLE_MANAGER);
    }

    @Transactional
    protected void createRoleIfNotExists(String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            Role newRole = new Role(roleName);
            roleRepository.save(newRole);
            log.info("Created new role: {}", roleName);
        } else {
            log.debug("Role already exists: {}", roleName);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getByName(String name) {
        return findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + name));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getDefaultRoles() {
        Set<Role> defaultRoles = new HashSet<>();
        Role userRole = getByName(ROLE_USER);
        defaultRoles.add(userRole);
        return defaultRoles;
    }


    @Transactional
    @Override
    public Role save(Role role) {
        log.debug("Saving role: {}", role.getName());
        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Transactional
    @Override
    public void delete(Role role) {
        log.debug("Deleting role: {}", role.getName());
        roleRepository.delete(role);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        findByName(name).ifPresent(role -> {
            log.debug("Deleting role by name: {}", name);
            roleRepository.delete(role);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getAdminRoles() {
            Set<Role> roles = new HashSet<>();
            roles.addAll(getDefaultUserRoles()); // Add default user roles first
            Role adminRole = findByName(ROLE_ADMIN)
                    .orElseThrow(() -> {
                        log.error("ROLE_ADMIN not found in database");
                        return new RoleNotFoundException("Role ROLE_ADMIN not found");
                    });
            roles.add(adminRole);
            log.debug("Retrieved admin roles: {}", roles);
            return roles;

    }


    @Transactional(readOnly = true)
    @Override
    public Set<Role> getManagerRoles() {
        Set<Role> roles = new HashSet<>();
        roles.addAll(getDefaultUserRoles());
        Role managerRole = findByName(ROLE_MANAGER)
                .orElseThrow(() -> {
                    log.error("ROLE_MANAGER not found in database");
                    return new RoleNotFoundException("Role ROLE_MANAGER not found");
                });
        roles.add(managerRole);
        log.debug("Retrieved manager roles: {}", roles);
        return roles;
    }



    @Transactional(readOnly = true)
    @Override
    public Set<Role> getDefaultUserRoles() {
        Set<Role> roles = new HashSet<>();
        Role userRole = findByName(ROLE_USER)
                .orElseThrow(() -> {
                    log.error("Default ROLE_USER not found in database");
                    return new RoleNotFoundException("Default role ROLE_USER not found");
                });
        roles.add(userRole);
        log.debug("Retrieved default user roles: {}", roles);
        return roles;
    }
}