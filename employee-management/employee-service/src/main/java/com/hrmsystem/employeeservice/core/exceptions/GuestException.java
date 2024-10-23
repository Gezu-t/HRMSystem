package com.hrmsystem.employeeservice.core.exceptions;

import java.io.Serial;

public class GuestException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = -5417447137798898360L;

    public GuestException(String message){

        super(message);
    }
}
