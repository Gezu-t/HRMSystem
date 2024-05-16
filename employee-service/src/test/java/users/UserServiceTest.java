package users;

import et.hrms.dal.dto.UserDTO;
import et.hrms.dal.mapping.UserMapper;
import et.hrms.dal.model.User;
import et.hrms.dal.repository.UserRepository;
import et.hrms.service.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
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
        userDTO.setPassword("testUser123");

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


}
