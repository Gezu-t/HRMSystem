package com.gtltek.employee.core.exceptions;

import java.io.Serial;

public class EmployeeNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3351528387843888907L;

    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
