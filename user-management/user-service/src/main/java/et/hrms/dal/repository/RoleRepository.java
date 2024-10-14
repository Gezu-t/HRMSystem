package et.hrms.dal.repository;

import et.hrms.dal.model.Role;
import et.hrms.dal.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
