package et.hrms.dal.dto.employee;

import et.hrms.dal.dto.education.EducationDTO;
import et.hrms.dal.model.GenderType;
import et.hrms.dal.model.MaritalStatus;
import et.hrms.dal.model.employee.EmployeeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank
    private String employeeNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private GenderType genderStatus;

    private MaritalStatus maritalStatus;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    private EmployeeType employeeType;

    private LocalDate dateOfLeaving;

    private LocalDate dateOfResignation;

    private Boolean employeeStatus;

    private LocalDate employeeStatusDate;

    private byte[] employeeProfileImage;

    private List<EmployeeAddressDTO> employeeAddresses;

    private List<EmployeeDetailDTO> employeeDetails;

    private List<EmployeeEvaluationDTO> employeeEvaluations;

    private List<EmployeePromotionDTO> employeePromotions;

    private List<EducationDTO> educationDTOS;
    private FamilyDTO familyDTO;
    private EmployeeAppearanceDTO employeeAppearanceDTO;

}

