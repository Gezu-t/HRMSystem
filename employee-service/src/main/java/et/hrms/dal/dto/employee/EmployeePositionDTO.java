package et.hrms.dal.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
public class EmployeePositionDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String jobTitle;

    @Size(max = 50)
    private String jobLevel;

    private LocalDateTime startDate;

    private Set<EmployeePositionManagementDTO> employeePositionManagements;

    // Getters and Setters
}
