package com.hrmsystem.employeeservice.core.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 199309394528422641L;

    public EntityNotFoundException(String message) {
        super(message);
    }


}
