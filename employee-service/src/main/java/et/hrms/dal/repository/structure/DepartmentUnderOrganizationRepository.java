package et.hrms.dal.repository.structure;

import et.hrms.dal.model.department.DepartmentUnderOrganization;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentUnderOrganizationRepository extends JpaRepository<DepartmentUnderOrganization, Long> {
    List<DepartmentUnderOrganization> findByOrganizationId(Long organizationId, Sort sort);



    @Query("SELECT d FROM DepartmentUnderOrganization d WHERE d.id = :departmentId")
    DepartmentUnderOrganization findDepartmentById(@Param("departmentId") Long departmentId);
}