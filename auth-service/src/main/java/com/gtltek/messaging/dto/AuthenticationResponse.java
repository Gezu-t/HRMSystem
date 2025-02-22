package com.gtltek.messaging.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;         // JWT Token
    private String refreshToken;  // Refresh token (if applicable)
    private String username;      // Authenticated username
    private String email;         // User email for profile display
    private List<String> roles;   // List of assigned roles
    private long expiresAt;       // Expiration timestamp (Unix epoch in ms)
}
