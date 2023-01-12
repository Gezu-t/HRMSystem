package et.hrms.dal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long employeeId;

    @NotEmpty(message = "Employee number cannot be empty!")
    private String employeeNo;

    @NotEmpty(message = "Employee first name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Employee last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Employee Date of birth cannot be empty!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String dateOfBirth;

    @NotEmpty(message = "Employee joining date cannot be empty!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String dateOfJoining;

    private List<DepartmentDTO> departmentDTOS;

    private List<EducationDTO>  educationDTOS;

    private FamilyDTO familyDTO;

    @NotEmpty(message = "Address must be specified")
    private List<AddressDTO> addressDTOS;

    private AppearanceDTO appearanceDTO;

    private List<EmployeePositionDTO> employeePositionDTOS;


}
