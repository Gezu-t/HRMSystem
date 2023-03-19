package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import et.hrms.controller.impl.UserControllerImpl;
import et.hrms.dal.dto.UserDTO;
import et.hrms.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserControllerImpl userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createUserTest() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("testUser");

        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUserDTO = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDTO))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"));

        verify(userService, times(1)).createUser(any(UserDTO.class));
    }


    @Test
    public void getUserByIdTest() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("testUser");

        when(userService.getUserById(1L)).thenReturn(userDTO);

        mockMvc.perform(get("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"));

        verify(userService, times(1)).getUserById(1L);
    }


    @Test
    public void updateUserTest() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUser");

        when(userService.updateUser(userDTO)).thenReturn(userDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUserDTO = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("updatedUser"));

        verify(userService, times(1)).updateUser(any(UserDTO.class));
    }

    @Test
    public void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setUsername("user1");

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setUsername("user2");

        List<UserDTO> users = Arrays.asList(user1, user2);

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
