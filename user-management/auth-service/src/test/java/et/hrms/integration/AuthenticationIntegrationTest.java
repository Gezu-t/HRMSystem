package et.hrms.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.dal.dto.AuthenticationRequest;
import et.hrms.dal.dto.RegisterRequest;
import et.hrms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    private static final String AUTH_BASE_URL = "/api/auth";

//    @Test
//    void whenRegisterWithValidData_thenReturns200() throws Exception {
//        RegisterRequest request = RegisterRequest.builder()
//                .username("testuser")
//                .password("password123")
//                .email("test@example.com")
//                .build();
//
//        mockMvc.perform(post(AUTH_BASE_URL + "/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username", is(request.getUsername())))
//                .andExpect(jsonPath("$.accessToken").exists())
//                .andExpect(jsonPath("$.refreshToken").exists());
//    }
//
//    @Test
//    void whenRegisterWithExistingUsername_thenReturns409() throws Exception {
//        RegisterRequest request = RegisterRequest.builder()
//                .username("existinguser")
//                .password("password123")
//                .email("new@example.com")
//                .build();
//
//        // First registration
//        mockMvc.perform(post(AUTH_BASE_URL + "/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request)));
//
//        // Second registration with same username
//        mockMvc.perform(post(AUTH_BASE_URL + "/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isConflict());
//    }
//
//    @Test
//    void whenLoginWithValidCredentials_thenReturns200() throws Exception {
//        // Register user first
//        RegisterRequest registerRequest = RegisterRequest.builder()
//                .username("logintest")
//                .password("password123")
//                .email("login@example.com")
//                .build();
//
//        mockMvc.perform(post(AUTH_BASE_URL + "/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(registerRequest)));
//
//        // Login
//        AuthenticationRequest loginRequest = AuthenticationRequest.builder()
//                .username("logintest")
//                .password("password123")
//                .build();
//
//        mockMvc.perform(post(AUTH_BASE_URL + "/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.accessToken").exists())
//                .andExpect(jsonPath("$.refreshToken").exists());
//    }
//
//    @Test
//    void whenAccessProtectedEndpointWithoutToken_thenReturns401() throws Exception {
//        mockMvc.perform(get("/api/protected"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void whenAccessProtectedEndpointWithAuth_thenReturns200() throws Exception {
//        mockMvc.perform(get("/api/protected"))
//                .andExpect(status().isOk());
//    }
}