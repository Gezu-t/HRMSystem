package et.hrms.client.validation;

import et.hrms.dal.dto.employee.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeValidationService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeValidationService.class);

    /**
     * Validates the status of an employee to ensure they are currently active.
     *
     * @param employee The employee data transferred object (DTO) to validate.
     * @throws IllegalStateException if the employee status is not active.
     */
    public void validateEmployeeStatus(EmployeeDTO employee) {
        // Null check for the entire employee object to prevent NullPointerException
        if (employee == null) {
            logger.error("Attempted to validate employee status but employee data is null");
            throw new IllegalArgumentException("Employee data must not be null");
        }

        // Check if the status is not "Active" or is null
        if (employee.getStatus() == null || !"Active".equalsIgnoreCase(employee.getStatus())) {
            logger.error("Employee status is not active. EmployeeId: {}, Status: {}", employee.getId(), employee.getStatus());
            throw new IllegalStateException("Employee status is not active for approval. EmployeeId: " + employee.getId());
        }

        logger.info("Employee status is active and valid for processing. EmployeeId: {}", employee.getId());
    }
}
