package et.hrms.service;


import et.hrms.config.SecurityTestConfig;
import et.hrms.dal.model.AuthUser;
import et.hrms.dal.model.Role;
import et.hrms.dal.repository.AuthUserRepository;
import et.hrms.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(SecurityTestConfig.class)
@TestPropertySource(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.compatibility-verifier.enabled=false"
})
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private AuthUserRepository userRepository;

    @Test
    void whenRegisterNewUser_thenSuccess() {
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        AuthUser user = userService.registerNewUser(
                "newuser",
                "password123",
                "new@example.com",
                Set.of(new Role("ROLE_USER"))
        );

        assertNotNull(user);
        assertEquals("newuser", user.getUsername());
        assertTrue(user.isEnabled());
    }

    @Test
    void whenRegisterExistingUsername_thenThrowsException() {
        when(userRepository.existsByUsername("existing")).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.registerNewUser(
                    "existing",
                    "password123",
                    "new@example.com",
                    Set.of(new Role("ROLE_USER"))
            );
        });
    }

    @Test
    void whenLoadNonExistentUser_thenThrowsException() {
        when(userRepository.findByUsername("nonexistent"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent");
        });
    }
}
