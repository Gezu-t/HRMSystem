package com.hrmsystem.employeeprofileservice.config.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessResponse {
    private String message;

    public SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
