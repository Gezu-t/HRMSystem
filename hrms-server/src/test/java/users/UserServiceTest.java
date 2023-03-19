package users;

import et.hrms.dal.dto.UserDTO;
import et.hrms.dal.mapping.UserMapper;
import et.hrms.dal.model.User;
import et.hrms.dal.repository.UserRepository;
import et.hrms.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");

        User user = UserMapper.INSTANCE.userDTOtoUser(userDTO);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUser = userService.createUser(userDTO);

        assertEquals(userDTO.getUsername(), createdUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getUserByIdTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserById(1L);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUser");

        when(userRepository.save(any(User.class))).thenReturn(UserMapper.INSTANCE.userDTOtoUser(userDTO));

        UserDTO updatedUserDTO = userService.updateUser(userDTO);

        assertEquals(userDTO.getId(), updatedUserDTO.getId());
        assertEquals(userDTO.getUsername(), updatedUserDTO.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllUsersTest() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOs = userService.getAllUsers();

        assertEquals(2, userDTOs.size());
        assertEquals(user1.getId(), userDTOs.get(0).getId());
        assertEquals(user1.getUsername(), userDTOs.get(0).getUsername());
        assertEquals(user2.getId(), userDTOs.get(1).getId());
        assertEquals(user2.getUsername(), userDTOs.get(1).getUsername());
        verify(userRepository, times(1)).findAll();
    }
}
