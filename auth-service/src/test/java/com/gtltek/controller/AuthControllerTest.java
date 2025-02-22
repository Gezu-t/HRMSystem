package com.gtltek.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtltek.messaging.repository.RoleRepository;
import com.gtltek.messaging.dto.RegisterRequest;
import com.gtltek.messaging.model.AuthUser;
import com.gtltek.exception.GlobalExceptionHandler;
import com.gtltek.service.JwtUtil;
import com.gtltek.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Limit the loaded context only to AuthController and GlobalExceptionHandler.
// This prevents the main application class (AuthServiceApplication) from being loaded.
@WebMvcTest(controllers = {AuthController.class, GlobalExceptionHandler.class})
@ContextConfiguration(classes = {AuthController.class, GlobalExceptionHandler.class})
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    // If RoleRepository is not used in your controller or advice, you may remove this.
    // Otherwise, keep it if needed by some bean in the slice.
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String VALID_USERNAME = "testuser";
    private static final String VALID_PASSWORD = "Password123!";
    private static final String VALID_EMAIL = "test@example.com";
    private static final String TEST_TOKEN = "test.jwt.token";
    private static final long TOKEN_EXPIRY = 3600000L;

    @BeforeEach
    void setUp() {
        // Since we now restrict the context to our controller and exception handler,
        // we rely on our own @MockBean definitions without interference from the main app.
        when(userService.loadUserByUsername(anyString()))
                .thenAnswer(invocation -> createMockUser());

        when(userService.findByUsername(anyString()))
                .thenReturn(Optional.of(createMockUser()));
    }

    @Test
    void register_WithValidRequest_ShouldReturnToken() throws Exception {
        // Arrange
        RegisterRequest request = createRegisterRequest();
        AuthUser mockUser = createMockUser();

        when(userService.registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL))
                .thenReturn(mockUser);
        when(jwtUtil.generateToken(VALID_USERNAME)).thenReturn(TEST_TOKEN);
        when(jwtUtil.getExpirationTime()).thenReturn(TOKEN_EXPIRY);

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value(TEST_TOKEN))
                .andExpect(jsonPath("$.username").value(VALID_USERNAME))
                .andExpect(jsonPath("$.email").value(VALID_EMAIL))
                .andExpect(jsonPath("$.roles").isArray())
                .andExpect(jsonPath("$.expiresAt").isNumber())
                .andDo(print());

        verify(userService).registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL);
        verify(jwtUtil).generateToken(VALID_USERNAME);
        verify(jwtUtil).getExpirationTime();
    }

    // Helper methods
    private RegisterRequest createRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername(VALID_USERNAME);
        request.setPassword(VALID_PASSWORD);
        request.setEmail(VALID_EMAIL);
        return request;
    }

    private AuthUser createMockUser() {
        return AuthUser.builder()
                .username(VALID_USERNAME)
                .email(VALID_EMAIL)
                .roles(new HashSet<>())
                .active(true)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }
}
