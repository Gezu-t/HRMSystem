package et.hrms.service;


import et.hrms.dal.model.AuthUser;
import et.hrms.dal.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    private AuthUser testUser;

    @BeforeEach
    void setUp() {
        Role userRole = new Role("ROLE_USER");
        testUser = new AuthUser(
                "testuser",
                "password",
                "test@example.com",
                Set.of(userRole)
        );
    }

    @Test
    void whenGenerateToken_thenTokenIsValid() {
        String token = jwtService.generateToken(testUser);

        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token, testUser));
    }

    @Test
    void whenExtractUsername_thenReturnsCorrectUsername() {
        String token = jwtService.generateToken(testUser);
        String username = jwtService.extractUsername(token);

        assertEquals(testUser.getUsername(), username);
    }

    @Test
    void whenTokenExpired_thenValidationFails() throws InterruptedException {
        String token = jwtService.generateToken(testUser);
        Thread.sleep(1000);

        assertFalse(jwtService.isTokenValid(token, testUser));
    }
}