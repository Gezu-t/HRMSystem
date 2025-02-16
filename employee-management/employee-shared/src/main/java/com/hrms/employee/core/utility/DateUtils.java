package com.hrms.employee.core.utility;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static int calculateYearsOfService(LocalDate hireDate) {
        return Period.between(hireDate, LocalDate.now()).getYears();
    }
}