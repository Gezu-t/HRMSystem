package com.gtltek.client.validation;

import com.gtltek.messaging.dto.employee.EmployeeAttendanceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeValidationService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeValidationService.class);

    /**
     * Validates the status of an employeeprofile to ensure they are currently active.
     *
     * @param employee The employeeprofile data transferred object (DTO) to validate.
     * @throws IllegalStateException if the employeeprofile status is not active.
     */
    public void validateEmployeeStatus(EmployeeAttendanceDTO employee) {
        // Null check for the entire employeeprofile object to prevent NullPointerException
        if (employee == null) {
            logger.error("Attempted to validate employeeprofile status but employeeprofile data is null");
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
