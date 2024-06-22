package et.hrms.dal.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class EmployeePositionManagementDTO {
    private Long id;

    private Long employeeId;

    private Long employeePositionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean creationStatus;

    private Boolean updateStatus;
}
