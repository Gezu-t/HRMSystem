package com.hrms.employee.core.exceptions;


import java.io.Serial;

public class BranchNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4468813406894752276L;

    public BranchNotFoundException(String message) {
        super(message);
    }
}
