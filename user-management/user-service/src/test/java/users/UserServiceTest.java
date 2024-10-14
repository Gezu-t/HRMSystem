package users;


import et.hrms.dal.dto.UserRequestDTO;
import et.hrms.dal.dto.UserResponseDTO;
import et.hrms.dal.mapper.UserMapper;
import et.hrms.dal.model.Role;
import et.hrms.dal.model.RoleName;
import et.hrms.dal.model.User;
import et.hrms.dal.repository.RoleRepository;
import et.hrms.dal.repository.UserRepository;
import et.hrms.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @Test
    public void createUserTest() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("testUser");
        userRequestDTO.setPassword("testUser123");
        userRequestDTO.setEmail("test@example.com");

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPassword");
        user.setEmail("test@example.com");
        user.setCreatedAt(Instant.now());

        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName(RoleName.ROLE_USER);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testUser");
        savedUser.setPassword("encodedPassword");
        savedUser.setEmail("test@example.com");
        savedUser.setRoles(Set.of(userRole));
        savedUser.setCreatedAt(user.getCreatedAt());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        // Mock methods
        when(userRepository.existsByUsername(userRequestDTO.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userRequestDTO.getPassword())).thenReturn("encodedPassword");

        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(userRole));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);


        // Act
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);

        // Assert
        assertEquals(userResponseDTO.getUsername(), createdUser.getUsername());
        assertEquals(userResponseDTO.getEmail(), createdUser.getEmail());
        assertEquals(userResponseDTO.getRoles(), createdUser.getRoles());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getUserByIdTest() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        // Mock methods
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));


        // Act
        UserResponseDTO result = userService.getUserById(1L);

        // Assert
        assertEquals(userResponseDTO.getId(), result.getId());
        assertEquals(userResponseDTO.getUsername(), result.getUsername());
        assertEquals(userResponseDTO.getEmail(), result.getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void updateUserTest() {
        // Arrange
        Long userId = 1L;
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("updated@example.com");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setUsername("testUser");
        existingUser.setEmail("test@example.com");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setUsername("testUser");
        updatedUser.setEmail("updated@example.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("updated@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        // Mock methods
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);


        // Act
        UserResponseDTO result = userService.updateUser(userId, userRequestDTO);

        // Assert
        assertEquals(userResponseDTO.getEmail(), result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
}


