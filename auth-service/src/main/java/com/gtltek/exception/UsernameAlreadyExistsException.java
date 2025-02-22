package com.gtltek.exception;


import java.io.Serial;

public class UsernameAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5554108070130977218L;

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
