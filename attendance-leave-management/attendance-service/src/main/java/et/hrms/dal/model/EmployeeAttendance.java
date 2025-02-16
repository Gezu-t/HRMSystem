package et.hrms.dal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "employee-attendance")
public class EmployeeAttendance implements Serializable {
    @Serial
    private static final long serialVersionUID = -6463770720615926129L;

    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String genderStatus;
    private String maritalStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String employeeType;
    private LocalDate dateOfLeaving;
    private LocalDate dateOfResignation;
    private Boolean employeeStatus;
    private LocalDate employeeStatusDate;
    private byte[] employeeProfileImage;

    private Long departmentId;
    private Long branchId;
    private Long organizationId;
    private String status;
}
