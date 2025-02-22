package com.gtltek.controller;

import com.gtltek.messaging.dto.AuthenticationRequest;
import com.gtltek.messaging.dto.AuthenticationResponse;
import com.gtltek.messaging.dto.RegisterRequest;
import com.gtltek.messaging.model.AuthUser;
import com.gtltek.messaging.model.Role;
import com.gtltek.service.JwtUtil;
import com.gtltek.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API for login, registration, and JWT handling")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Register a new user", description = "Creates a new user and returns a JWT token.")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        // Register new user
        AuthUser user = userService.registerNewUser(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );

        // Safely handle roles
        List<String> roles = Optional.ofNullable(user)
                .map(AuthUser::getRoles)
                .map(roleSet -> roleSet.stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        // Generate JWT token
        assert user != null;
        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(createAuthResponse(user, token, roles));
    }

    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token.")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        // Authenticate
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Get user details
        AuthUser user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + request.getUsername()));

        // Safely handle roles
        List<String> roles = Optional.ofNullable(user.getRoles())
                .map(roleSet -> roleSet.stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(createAuthResponse(user, token, roles));
    }

    // Helper method to create AuthenticationResponse
    private AuthenticationResponse createAuthResponse(AuthUser user, String token, List<String> roles) {
        return AuthenticationResponse.builder()
                .token(token)
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .expiresAt(System.currentTimeMillis() + jwtUtil.getExpirationTime())
                .build();
    }
}
