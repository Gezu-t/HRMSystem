package et.hrms.dal.repository.structure;

import et.hrms.dal.model.department.DepartmentUnderBranch;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentUnderBranchRepository extends JpaRepository<DepartmentUnderBranch, Long> {

    @Query("SELECT d FROM DepartmentUnderBranch d WHERE d.branch.id = :branchId")
    List<DepartmentUnderBranch> findByBranchId(Long branchId, Sort sort);

    @Query("SELECT d FROM DepartmentUnderBranch d WHERE d.branch.id = :branchId")
    List<DepartmentUnderBranch> findByBranchId(@Param("branchId") Long branchId);

    @Query("SELECT d FROM DepartmentUnderBranch d WHERE d.branch.id = :branchId")
    List<DepartmentUnderBranch> findDepartmentsByBranchId(@Param("branchId") Long branchId);


    @Query("SELECT d FROM DepartmentUnderBranch d WHERE d.id = :departmentId")
    DepartmentUnderBranch findDepartmentById(@Param("departmentId") Long departmentId);


}