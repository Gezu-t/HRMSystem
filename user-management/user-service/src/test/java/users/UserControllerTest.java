package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.UserControllerImpl;
import et.hrms.dal.dto.UserRequestDTO;
import et.hrms.dal.dto.UserResponseDTO;
import et.hrms.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserControllerImpl userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createUserTest() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("testUser");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setEmail("test@example.com");

        when(userService.createUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUserRequestDTO = objectMapper.writeValueAsString(userRequestDTO);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserRequestDTO))
                .andExpect(status().isOk()) // Assuming the controller returns 200 OK
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"));

        verify(userService, times(1)).createUser(any(UserRequestDTO.class));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void getUserByIdTest() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        when(userService.getUserById(1L)).thenReturn(userResponseDTO);

        mockMvc.perform(get("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void updateUserTest() throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("updated@example.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("updated@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        when(userService.updateUser(eq(1L), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUserRequestDTO = objectMapper.writeValueAsString(userRequestDTO);

        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserRequestDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("updated@example.com"));

        verify(userService, times(1)).updateUser(eq(1L), any(UserRequestDTO.class));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void getAllUsersTest() throws Exception {
        UserResponseDTO user1 = new UserResponseDTO();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setRoles(Set.of("ROLE_USER"));

        UserResponseDTO user2 = new UserResponseDTO();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setRoles(Set.of("ROLE_USER"));

        List<UserResponseDTO> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].username").value("user1"))
                .andExpect(jsonPath("$.[1].id").value(2L))
                .andExpect(jsonPath("$.[1].username").value("user2"));

        verify(userService, times(1)).getAllUsers();
    }
}
