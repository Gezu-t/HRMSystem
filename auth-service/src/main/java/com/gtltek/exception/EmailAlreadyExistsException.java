package com.gtltek.exception;


import java.io.Serial;

public class EmailAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4674371478526730481L;

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
