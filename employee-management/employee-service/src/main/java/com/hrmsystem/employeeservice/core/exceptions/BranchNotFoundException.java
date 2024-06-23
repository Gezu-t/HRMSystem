package com.hrmsystem.employeeservice.core.exceptions;



public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(String message) {
        super(message);
    }
}
