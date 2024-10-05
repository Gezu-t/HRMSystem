package com.hrmsystem.employeeservice.core.dal.mapping.employee;

import com.hrmsystem.employeeservice.core.dal.mapping.education.EducationMapper;
import dal.dto.education.EducationDTO;
import dal.dto.employee.EmployeeAddressDTO;
import dal.dto.employee.EmployeeDTO;
import dal.dto.employee.EmployeeDetailDTO;
import dal.dto.employee.EmployeeEvaluationDTO;
import dal.dto.employee.EmployeePositionManagementDTO;
import dal.dto.employee.EmployeePromotionDTO;
import dal.model.branch.Branch;
import dal.model.department.Department;
import dal.model.education.Education;
import dal.model.employee.Employee;
import dal.model.employee.EmployeeAddress;
import dal.model.employee.EmployeeDetail;
import dal.model.employee.EmployeeEvaluation;
import dal.model.employee.EmployeePositionManagement;
import dal.model.employee.EmployeePromotion;
import dal.model.organization.Organization;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private EmployeeAppearanceMapper employeeAppearanceMapper;
    @Autowired
    private FamilyMapper familyMapper;
    @Autowired
    private EmployeeEvaluationMapper employeeEvaluationMapper;
    @Autowired
    private EmployeeAddressMapper employeeAddressMapper;
    @Autowired
    private EmployeeDetailMapper employeeDetailMapper;
    @Autowired
    private EmployeePromotionMapper employeePromotionMapper;
    @Autowired
    private EducationMapper educationMapper;

    @Override
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setDepartmentId( employeeDepartmentId( employee ) );
        employeeDTO.setBranchId( employeeBranchId( employee ) );
        employeeDTO.setOrganizationId( employeeOrganizationId( employee ) );
        employeeDTO.setId( employee.getId() );
        employeeDTO.setEmployeeNumber( employee.getEmployeeNumber() );
        employeeDTO.setFirstName( employee.getFirstName() );
        employeeDTO.setLastName( employee.getLastName() );
        if ( employee.getGenderStatus() != null ) {
            employeeDTO.setGenderStatus( employee.getGenderStatus().name() );
        }
        if ( employee.getMaritalStatus() != null ) {
            employeeDTO.setMaritalStatus( employee.getMaritalStatus().name() );
        }
        employeeDTO.setDateOfBirth( employee.getDateOfBirth() );
        employeeDTO.setDateOfJoining( employee.getDateOfJoining() );
        if ( employee.getEmployeeType() != null ) {
            employeeDTO.setEmployeeType( employee.getEmployeeType().name() );
        }
        employeeDTO.setDateOfLeaving( employee.getDateOfLeaving() );
        employeeDTO.setDateOfResignation( employee.getDateOfResignation() );
        employeeDTO.setEmployeeStatus( employee.getEmployeeStatus() );
        employeeDTO.setEmployeeStatusDate( employee.getEmployeeStatusDate() );
        byte[] employeeProfileImage = employee.getEmployeeProfileImage();
        if ( employeeProfileImage != null ) {
            employeeDTO.setEmployeeProfileImage( Arrays.copyOf( employeeProfileImage, employeeProfileImage.length ) );
        }
        employeeDTO.setEmployeeAppearance( employeeAppearanceMapper.toAppearanceDTO( employee.getEmployeeAppearance() ) );
        employeeDTO.setFamily( familyMapper.toFamilyDTO( employee.getFamily() ) );
        employeeDTO.setEmployeePositionManagements( employeePositionManagementSetToEmployeePositionManagementDTOList( employee.getEmployeePositionManagements() ) );
        employeeDTO.setEmployeeEvaluations( employeeEvaluationListToEmployeeEvaluationDTOList( employee.getEmployeeEvaluations() ) );
        employeeDTO.setEmployeeAddresses( employeeAddressListToEmployeeAddressDTOList( employee.getEmployeeAddresses() ) );
        employeeDTO.setEmployeeDetails( employeeDetailMapper.toEmployeeDetailDTOs( employee.getEmployeeDetails() ) );
        employeeDTO.setEmployeePromotions( employeePromotionListToEmployeePromotionDTOList( employee.getEmployeePromotions() ) );
        employeeDTO.setEducations( educationListToEducationDTOList( employee.getEducations() ) );

        return employeeDTO;
    }

    @Override
    public Employee toEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDTO.getId() );
        employee.setEmployeeNumber( employeeDTO.getEmployeeNumber() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setLastName( employeeDTO.getLastName() );
        employee.setDateOfBirth( employeeDTO.getDateOfBirth() );
        employee.setDateOfJoining( employeeDTO.getDateOfJoining() );
        employee.setDateOfLeaving( employeeDTO.getDateOfLeaving() );
        employee.setDateOfResignation( employeeDTO.getDateOfResignation() );
        employee.setEmployeeStatus( employeeDTO.getEmployeeStatus() );
        employee.setEmployeeStatusDate( employeeDTO.getEmployeeStatusDate() );
        byte[] employeeProfileImage = employeeDTO.getEmployeeProfileImage();
        if ( employeeProfileImage != null ) {
            employee.setEmployeeProfileImage( Arrays.copyOf( employeeProfileImage, employeeProfileImage.length ) );
        }
        employee.setEmployeeAppearance( employeeAppearanceMapper.toAppearance( employeeDTO.getEmployeeAppearance() ) );
        employee.setFamily( familyMapper.toFamily( employeeDTO.getFamily() ) );
        employee.setEmployeePositionManagements( employeePositionManagementDTOListToEmployeePositionManagementSet( employeeDTO.getEmployeePositionManagements() ) );
        employee.setEmployeeEvaluations( employeeEvaluationDTOListToEmployeeEvaluationList( employeeDTO.getEmployeeEvaluations() ) );
        employee.setEmployeeAddresses( employeeAddressDTOListToEmployeeAddressList( employeeDTO.getEmployeeAddresses() ) );
        employee.setEmployeeDetails( employeeDetailDTOListToEmployeeDetailList( employeeDTO.getEmployeeDetails() ) );
        employee.setEmployeePromotions( employeePromotionDTOListToEmployeePromotionList( employeeDTO.getEmployeePromotions() ) );
        employee.setEducations( educationDTOListToEducationList( employeeDTO.getEducations() ) );

        setEnumAndEntityValues( employeeDTO, employee );

        return employee;
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO, Employee employee) {
        if ( employeeDTO == null ) {
            return;
        }

        employee.setId( employeeDTO.getId() );
        employee.setEmployeeNumber( employeeDTO.getEmployeeNumber() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setLastName( employeeDTO.getLastName() );
        employee.setDateOfBirth( employeeDTO.getDateOfBirth() );
        employee.setDateOfJoining( employeeDTO.getDateOfJoining() );
        employee.setDateOfLeaving( employeeDTO.getDateOfLeaving() );
        employee.setDateOfResignation( employeeDTO.getDateOfResignation() );
        employee.setEmployeeStatus( employeeDTO.getEmployeeStatus() );
        employee.setEmployeeStatusDate( employeeDTO.getEmployeeStatusDate() );
        byte[] employeeProfileImage = employeeDTO.getEmployeeProfileImage();
        if ( employeeProfileImage != null ) {
            employee.setEmployeeProfileImage( Arrays.copyOf( employeeProfileImage, employeeProfileImage.length ) );
        }
        else {
            employee.setEmployeeProfileImage( null );
        }
        employee.setEmployeeAppearance( employeeAppearanceMapper.toAppearance( employeeDTO.getEmployeeAppearance() ) );
        employee.setFamily( familyMapper.toFamily( employeeDTO.getFamily() ) );
        if ( employee.getEmployeePositionManagements() != null ) {
            Set<EmployeePositionManagement> set = employeePositionManagementDTOListToEmployeePositionManagementSet( employeeDTO.getEmployeePositionManagements() );
            if ( set != null ) {
                employee.getEmployeePositionManagements().clear();
                employee.getEmployeePositionManagements().addAll( set );
            }
            else {
                employee.setEmployeePositionManagements( null );
            }
        }
        else {
            Set<EmployeePositionManagement> set = employeePositionManagementDTOListToEmployeePositionManagementSet( employeeDTO.getEmployeePositionManagements() );
            if ( set != null ) {
                employee.setEmployeePositionManagements( set );
            }
        }
        if ( employee.getEmployeeEvaluations() != null ) {
            List<EmployeeEvaluation> list = employeeEvaluationDTOListToEmployeeEvaluationList( employeeDTO.getEmployeeEvaluations() );
            if ( list != null ) {
                employee.getEmployeeEvaluations().clear();
                employee.getEmployeeEvaluations().addAll( list );
            }
            else {
                employee.setEmployeeEvaluations( null );
            }
        }
        else {
            List<EmployeeEvaluation> list = employeeEvaluationDTOListToEmployeeEvaluationList( employeeDTO.getEmployeeEvaluations() );
            if ( list != null ) {
                employee.setEmployeeEvaluations( list );
            }
        }
        if ( employee.getEmployeeAddresses() != null ) {
            List<EmployeeAddress> list1 = employeeAddressDTOListToEmployeeAddressList( employeeDTO.getEmployeeAddresses() );
            if ( list1 != null ) {
                employee.getEmployeeAddresses().clear();
                employee.getEmployeeAddresses().addAll( list1 );
            }
            else {
                employee.setEmployeeAddresses( null );
            }
        }
        else {
            List<EmployeeAddress> list1 = employeeAddressDTOListToEmployeeAddressList( employeeDTO.getEmployeeAddresses() );
            if ( list1 != null ) {
                employee.setEmployeeAddresses( list1 );
            }
        }
        if ( employee.getEmployeeDetails() != null ) {
            List<EmployeeDetail> list2 = employeeDetailDTOListToEmployeeDetailList( employeeDTO.getEmployeeDetails() );
            if ( list2 != null ) {
                employee.getEmployeeDetails().clear();
                employee.getEmployeeDetails().addAll( list2 );
            }
            else {
                employee.setEmployeeDetails( null );
            }
        }
        else {
            List<EmployeeDetail> list2 = employeeDetailDTOListToEmployeeDetailList( employeeDTO.getEmployeeDetails() );
            if ( list2 != null ) {
                employee.setEmployeeDetails( list2 );
            }
        }
        if ( employee.getEmployeePromotions() != null ) {
            List<EmployeePromotion> list3 = employeePromotionDTOListToEmployeePromotionList( employeeDTO.getEmployeePromotions() );
            if ( list3 != null ) {
                employee.getEmployeePromotions().clear();
                employee.getEmployeePromotions().addAll( list3 );
            }
            else {
                employee.setEmployeePromotions( null );
            }
        }
        else {
            List<EmployeePromotion> list3 = employeePromotionDTOListToEmployeePromotionList( employeeDTO.getEmployeePromotions() );
            if ( list3 != null ) {
                employee.setEmployeePromotions( list3 );
            }
        }
        if ( employee.getEducations() != null ) {
            List<Education> list4 = educationDTOListToEducationList( employeeDTO.getEducations() );
            if ( list4 != null ) {
                employee.getEducations().clear();
                employee.getEducations().addAll( list4 );
            }
            else {
                employee.setEducations( null );
            }
        }
        else {
            List<Education> list4 = educationDTOListToEducationList( employeeDTO.getEducations() );
            if ( list4 != null ) {
                employee.setEducations( list4 );
            }
        }

        setEnumAndEntityValues( employeeDTO, employee );
    }

    private Long employeeDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Long id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long employeeBranchId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Branch branch = employee.getBranch();
        if ( branch == null ) {
            return null;
        }
        Long id = branch.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long employeeOrganizationId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Organization organization = employee.getOrganization();
        if ( organization == null ) {
            return null;
        }
        Long id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected EmployeePositionManagementDTO employeePositionManagementToEmployeePositionManagementDTO(EmployeePositionManagement employeePositionManagement) {
        if ( employeePositionManagement == null ) {
            return null;
        }

        EmployeePositionManagementDTO employeePositionManagementDTO = new EmployeePositionManagementDTO();

        employeePositionManagementDTO.setId( employeePositionManagement.getId() );
        employeePositionManagementDTO.setCreatedAt( employeePositionManagement.getCreatedAt() );
        employeePositionManagementDTO.setUpdatedAt( employeePositionManagement.getUpdatedAt() );
        employeePositionManagementDTO.setCreationStatus( employeePositionManagement.getCreationStatus() );
        employeePositionManagementDTO.setUpdateStatus( employeePositionManagement.getUpdateStatus() );

        return employeePositionManagementDTO;
    }

    protected List<EmployeePositionManagementDTO> employeePositionManagementSetToEmployeePositionManagementDTOList(Set<EmployeePositionManagement> set) {
        if ( set == null ) {
            return null;
        }

        List<EmployeePositionManagementDTO> list = new ArrayList<EmployeePositionManagementDTO>( set.size() );
        for ( EmployeePositionManagement employeePositionManagement : set ) {
            list.add( employeePositionManagementToEmployeePositionManagementDTO( employeePositionManagement ) );
        }

        return list;
    }

    protected List<EmployeeEvaluationDTO> employeeEvaluationListToEmployeeEvaluationDTOList(List<EmployeeEvaluation> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeEvaluationDTO> list1 = new ArrayList<EmployeeEvaluationDTO>( list.size() );
        for ( EmployeeEvaluation employeeEvaluation : list ) {
            list1.add( employeeEvaluationMapper.toEmployeeEvaluation( employeeEvaluation ) );
        }

        return list1;
    }

    protected List<EmployeeAddressDTO> employeeAddressListToEmployeeAddressDTOList(List<EmployeeAddress> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeAddressDTO> list1 = new ArrayList<EmployeeAddressDTO>( list.size() );
        for ( EmployeeAddress employeeAddress : list ) {
            list1.add( employeeAddressMapper.toEmployeeAddressDTO( employeeAddress ) );
        }

        return list1;
    }

    protected List<EmployeePromotionDTO> employeePromotionListToEmployeePromotionDTOList(List<EmployeePromotion> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeePromotionDTO> list1 = new ArrayList<EmployeePromotionDTO>( list.size() );
        for ( EmployeePromotion employeePromotion : list ) {
            list1.add( employeePromotionMapper.toEmployeePromotionDTO( employeePromotion ) );
        }

        return list1;
    }

    protected List<EducationDTO> educationListToEducationDTOList(List<Education> list) {
        if ( list == null ) {
            return null;
        }

        List<EducationDTO> list1 = new ArrayList<EducationDTO>( list.size() );
        for ( Education education : list ) {
            list1.add( educationMapper.toEducationDTO( education ) );
        }

        return list1;
    }

    protected EmployeePositionManagement employeePositionManagementDTOToEmployeePositionManagement(EmployeePositionManagementDTO employeePositionManagementDTO) {
        if ( employeePositionManagementDTO == null ) {
            return null;
        }

        EmployeePositionManagement employeePositionManagement = new EmployeePositionManagement();

        employeePositionManagement.setId( employeePositionManagementDTO.getId() );
        employeePositionManagement.setCreatedAt( employeePositionManagementDTO.getCreatedAt() );
        employeePositionManagement.setUpdatedAt( employeePositionManagementDTO.getUpdatedAt() );
        employeePositionManagement.setCreationStatus( employeePositionManagementDTO.getCreationStatus() );
        employeePositionManagement.setUpdateStatus( employeePositionManagementDTO.getUpdateStatus() );

        return employeePositionManagement;
    }

    protected Set<EmployeePositionManagement> employeePositionManagementDTOListToEmployeePositionManagementSet(List<EmployeePositionManagementDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<EmployeePositionManagement> set = new LinkedHashSet<EmployeePositionManagement>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( EmployeePositionManagementDTO employeePositionManagementDTO : list ) {
            set.add( employeePositionManagementDTOToEmployeePositionManagement( employeePositionManagementDTO ) );
        }

        return set;
    }

    protected List<EmployeeEvaluation> employeeEvaluationDTOListToEmployeeEvaluationList(List<EmployeeEvaluationDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeEvaluation> list1 = new ArrayList<EmployeeEvaluation>( list.size() );
        for ( EmployeeEvaluationDTO employeeEvaluationDTO : list ) {
            list1.add( employeeEvaluationMapper.toEmployeeEvaluation( employeeEvaluationDTO ) );
        }

        return list1;
    }

    protected List<EmployeeAddress> employeeAddressDTOListToEmployeeAddressList(List<EmployeeAddressDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeAddress> list1 = new ArrayList<EmployeeAddress>( list.size() );
        for ( EmployeeAddressDTO employeeAddressDTO : list ) {
            list1.add( employeeAddressMapper.toEmployeeAddress( employeeAddressDTO ) );
        }

        return list1;
    }

    protected List<EmployeeDetail> employeeDetailDTOListToEmployeeDetailList(List<EmployeeDetailDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeDetail> list1 = new ArrayList<EmployeeDetail>( list.size() );
        for ( EmployeeDetailDTO employeeDetailDTO : list ) {
            list1.add( employeeDetailMapper.toEmployeeDetail( employeeDetailDTO ) );
        }

        return list1;
    }

    protected List<EmployeePromotion> employeePromotionDTOListToEmployeePromotionList(List<EmployeePromotionDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeePromotion> list1 = new ArrayList<EmployeePromotion>( list.size() );
        for ( EmployeePromotionDTO employeePromotionDTO : list ) {
            list1.add( employeePromotionMapper.toEmployeePromotion( employeePromotionDTO ) );
        }

        return list1;
    }

    protected List<Education> educationDTOListToEducationList(List<EducationDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Education> list1 = new ArrayList<Education>( list.size() );
        for ( EducationDTO educationDTO : list ) {
            list1.add( educationMapper.toEducation( educationDTO ) );
        }

        return list1;
    }
}
