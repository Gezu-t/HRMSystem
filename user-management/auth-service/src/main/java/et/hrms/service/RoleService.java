package et.hrms.service;

import et.hrms.dal.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {


    @Transactional
    void createDefaultRolesIfNotExist();

    @Transactional(readOnly = true)
    Optional<Role> findByName(String name);

    @Transactional(readOnly = true)
    Role getByName(String name);

    @Transactional(readOnly = true)
    Set<Role> getDefaultRoles();

    @Transactional
    Role save(Role role);

    @Transactional(readOnly = true)
    List<Role> findAll();

    @Transactional(readOnly = true)
    boolean existsByName(String name);

    @Transactional
    void delete(Role role);

    @Transactional
    void deleteByName(String name);

    @Transactional(readOnly = true)
    Set<Role> getAdminRoles();

    @Transactional(readOnly = true)
    Set<Role> getManagerRoles();

    @Transactional(readOnly = true)
    Set<Role> getDefaultUserRoles();
}
