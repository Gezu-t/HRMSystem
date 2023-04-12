package et.hrms.dal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.EmployeeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class EmployeeDTO {

    private Long employeeId;

    @NotEmpty(message = "Employee number cannot be empty!")
    private String employeeNumber;

    @NotEmpty(message = "Employee first name cannot be empty!")
    private String firstName;

    @NotEmpty(message = "Employee last name cannot be empty!")
    private String lastName;

    @NotEmpty(message = "Employee Date of birth cannot be empty!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Employee joining date cannot be empty!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfJoining;

    private String orgName;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    private List<EmployeeAddressDTO> employeeAddressDTOS;

    private List<EmployeeDetailDTO> employeeDetailDTOS;




}
