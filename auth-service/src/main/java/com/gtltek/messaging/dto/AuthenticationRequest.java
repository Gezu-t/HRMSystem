package com.gtltek.messaging.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter@AllArgsConstructor@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}