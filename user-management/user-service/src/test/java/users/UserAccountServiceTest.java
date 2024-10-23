package users;


import et.hrms.dal.dto.UserRequestDTO;
import et.hrms.dal.dto.UserResponseDTO;
import et.hrms.dal.mapper.UserMapper;
import et.hrms.dal.model.Role;
import et.hrms.dal.model.RoleName;
import et.hrms.dal.model.UserAccount;
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
public class UserAccountServiceTest {

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
        userRequestDTO.setRoles(Set.of("ROLE_USER")); // Ensure roles are set

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("testUser");
        userAccount.setPasswordHash("encodedPassword");
        userAccount.setEmail("test@example.com");
        userAccount.setCreatedAt(Instant.now());

        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName(RoleName.ROLE_USER);

        UserAccount savedUserAccount = new UserAccount();
        savedUserAccount.setId(1L);
        savedUserAccount.setUsername("testUser");
        savedUserAccount.setPasswordHash("encodedPassword");
        savedUserAccount.setEmail("test@example.com");
        savedUserAccount.setRoles(Set.of(userRole));
        savedUserAccount.setCreatedAt(userAccount.getCreatedAt());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        // Mock the necessary calls
        when(userRepository.existsByUsername(userRequestDTO.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userRequestDTO.getPassword())).thenReturn("encodedPassword");

        // Mock the userMapper to return a valid UserAccount entity when converting from the DTO
        when(userMapper.toEntity(any(UserRequestDTO.class))).thenReturn(userAccount);

        // Mock finding the role
        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(userRole));

        // Mock saving the user and converting back to DTO
        when(userRepository.save(any(UserAccount.class))).thenReturn(savedUserAccount);
        when(userMapper.toDto(any(UserAccount.class))).thenReturn(userResponseDTO); // Mock this

        // Act
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);

        // Assert
        assertEquals(userResponseDTO.getUsername(), createdUser.getUsername()); // Assert the result
        assertEquals(userResponseDTO.getEmail(), createdUser.getEmail());
        assertEquals(userResponseDTO.getRoles(), createdUser.getRoles());
        verify(userRepository, times(1)).save(any(UserAccount.class));
    }




    @Test
    public void getUserByIdTest() {
        // Arrange
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1L);
        userAccount.setUsername("testUser");
        userAccount.setEmail("test@example.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("test@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        // Mock methods
        when(userRepository.findById(1L)).thenReturn(Optional.of(userAccount));
        when(userMapper.toDto(userAccount)).thenReturn(userResponseDTO); // Mock this

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

        UserAccount existingUserAccount = new UserAccount();
        existingUserAccount.setId(userId);
        existingUserAccount.setUsername("testUser");
        existingUserAccount.setEmail("test@example.com");

        UserAccount updatedUserAccount = new UserAccount();
        updatedUserAccount.setId(userId);
        updatedUserAccount.setUsername("testUser");
        updatedUserAccount.setEmail("updated@example.com");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setUsername("testUser");
        userResponseDTO.setEmail("updated@example.com");
        userResponseDTO.setRoles(Set.of("ROLE_USER"));

        // Mock methods
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserAccount));
        when(userRepository.save(any(UserAccount.class))).thenReturn(updatedUserAccount);
        when(userMapper.toDto(updatedUserAccount)).thenReturn(userResponseDTO); // Mock this

        // Act
        UserResponseDTO result = userService.updateUser(userId, userRequestDTO);

        // Assert
        assertEquals(userResponseDTO.getEmail(), result.getEmail());
        verify(userRepository, times(1)).save(any(UserAccount.class));
    }

}


