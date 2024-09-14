package com.hrmsystem.employeeservice.core.dal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    private Long id;
    @NotNull(message = "user name must not be null")
    @Max(150)
    @Min(2)
    private String username;
    @NotNull(message = "Password must not be null")
    @Max(200)
    @Min(8)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "user name must not be null") @Max(150) @Min(2) String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "user name must not be null") @Max(150) @Min(2) String username) {
        this.username = username;
    }

    public @NotNull(message = "Password must not be null") @Max(200) @Min(8) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password must not be null") @Max(200) @Min(8) String password) {
        this.password = password;
    }
}
