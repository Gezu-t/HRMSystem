package et.hrms.dal.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import et.hrms.dal.model.employee.EmployeeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
public class EmployeeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4236763029212018205L;


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


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public @NotEmpty(message = "Employee number cannot be empty!") String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(@NotEmpty(message = "Employee number cannot be empty!") String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public @NotEmpty(message = "Employee first name cannot be empty!") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "Employee first name cannot be empty!") String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty(message = "Employee last name cannot be empty!") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "Employee last name cannot be empty!") String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty(message = "Employee Date of birth cannot be empty!") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotEmpty(message = "Employee Date of birth cannot be empty!") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotEmpty(message = "Employee joining date cannot be empty!") LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(@NotEmpty(message = "Employee joining date cannot be empty!") LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public List<EmployeeAddressDTO> getEmployeeAddressDTOS() {
        return employeeAddressDTOS;
    }

    public void setEmployeeAddressDTOS(List<EmployeeAddressDTO> employeeAddressDTOS) {
        this.employeeAddressDTOS = employeeAddressDTOS;
    }

    public List<EmployeeDetailDTO> getEmployeeDetailDTOS() {
        return employeeDetailDTOS;
    }

    public void setEmployeeDetailDTOS(List<EmployeeDetailDTO> employeeDetailDTOS) {
        this.employeeDetailDTOS = employeeDetailDTOS;
    }
}
