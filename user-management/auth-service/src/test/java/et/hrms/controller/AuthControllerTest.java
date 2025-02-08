package et.hrms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.dal.dto.AuthenticationRequest;
import et.hrms.dal.dto.RegisterRequest;
import et.hrms.dal.model.AuthUser;
import et.hrms.dal.repository.RoleRepository;
import et.hrms.exception.GlobalExceptionHandler;
import et.hrms.exception.UsernameAlreadyExistsException;
import et.hrms.service.JwtUtil;
import et.hrms.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {AuthController.class, GlobalExceptionHandler.class})
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
        MockitoAnnotations.openMocks(this);
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

//    @Test
//    void login_WithValidCredentials_ShouldReturnToken() throws Exception {
//        // Arrange
//        AuthenticationRequest request = createAuthRequest();
//        AuthUser mockUser = createMockUser();
//
//        when(userService.loadUserByUsername(VALID_USERNAME)).thenReturn(mockUser);
//        when(userService.findByUsername(VALID_USERNAME)).thenReturn(Optional.of(mockUser));
//        when(jwtUtil.generateToken(VALID_USERNAME)).thenReturn(TEST_TOKEN);
//        when(jwtUtil.getExpirationTime()).thenReturn(TOKEN_EXPIRY);
//
//        // Act & Assert
//        mockMvc.perform(post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.token").value(TEST_TOKEN))
//                .andExpect(jsonPath("$.username").value(VALID_USERNAME))
//                .andExpect(jsonPath("$.email").value(VALID_EMAIL))
//                .andExpect(jsonPath("$.roles").isArray())
//                .andExpect(jsonPath("$.expiresAt").isNumber())
//                .andDo(print());
//
//        verify(authenticationManager).authenticate(
//                new UsernamePasswordAuthenticationToken(VALID_USERNAME, VALID_PASSWORD));
//        verify(userService).loadUserByUsername(VALID_USERNAME);
//        verify(jwtUtil).generateToken(VALID_USERNAME);
//    }

//    @Test
//    void register_WithExistingUsername_ShouldReturnConflict() throws Exception {
//        // Arrange
//        RegisterRequest request = createRegisterRequest();
//
//        when(userService.registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL))
//                .thenThrow(new UsernameAlreadyExistsException("Username already exists"));
//
//        // Act & Assert
//        mockMvc.perform(post("/api/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isConflict())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.error").value("USERNAME_EXISTS"))
//                .andExpect(jsonPath("$.message").value("Username already exists"))
//                .andDo(print());
//
//        verify(userService).registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL);
//    }

//    @Test
//    void register_WithInvalidRequest_ShouldReturnBadRequest() throws Exception {
//        // Arrange
//        RegisterRequest request = new RegisterRequest(); // Empty request
//
//        // Act & Assert
//        mockMvc.perform(post("/api/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.error").value("VALIDATION_FAILED"))
//                .andDo(print());
//    }

//    @Test
//    void login_WithInvalidCredentials_ShouldReturnUnauthorized() throws Exception {
//        // Arrange
//        AuthenticationRequest request = createAuthRequest();
//
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenThrow(new BadCredentialsException("Invalid credentials"));
//
//        // Act & Assert
//        mockMvc.perform(post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.error").value("INVALID_CREDENTIALS"))
//                .andExpect(jsonPath("$.message").value("Invalid username or password"))
//                .andDo(print());
//
//        verify(authenticationManager).authenticate(
//                new UsernamePasswordAuthenticationToken(VALID_USERNAME, VALID_PASSWORD));
//    }

    // Helper methods
    private RegisterRequest createRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername(VALID_USERNAME);
        request.setPassword(VALID_PASSWORD);
        request.setEmail(VALID_EMAIL);
        return request;
    }

    private AuthenticationRequest createAuthRequest() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername(VALID_USERNAME);
        request.setPassword(VALID_PASSWORD);
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