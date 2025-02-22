package com.gtltek.service;

import com.gtltek.messaging.model.AuthUser;
import com.gtltek.messaging.repository.AuthUserRepository;
import com.gtltek.exception.EmailAlreadyExistsException;
import com.gtltek.exception.UsernameAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * A minimal test class with just enough to verify that
 * your stubs and service calls align.
 *
 * Make sure:
 *  - The same strings are used in your service calls and stubs.
 *  - 'UserService' is the same instance being tested.
 *  - 'AuthUserRepository' method names match exactly.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private AuthUserRepository authUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    // Test data constants
    private static final String VALID_USERNAME = "testuser";
    private static final String VALID_PASSWORD = "Password123!";
    private static final String VALID_EMAIL = "test@example.com";
    private static final String ENCODED_PASSWORD = "encodedPassword123";

    @Test
    void registerNewUser_WithValidData_ShouldCreateUser() {
        // Arrange
        when(authUserRepository.existsByUsername(VALID_USERNAME)).thenReturn(false);
        when(authUserRepository.existsByEmail(VALID_EMAIL)).thenReturn(false);
        when(passwordEncoder.encode(VALID_PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(authUserRepository.save(any(AuthUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        AuthUser result = userService.registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL);

        // Assert
        assertNotNull(result);
        assertEquals(VALID_USERNAME, result.getUsername());
        assertEquals(ENCODED_PASSWORD, result.getPassword());
        assertEquals(VALID_EMAIL, result.getEmail());
        assertTrue(result.isActive());
        assertTrue(result.isEnabled());
        assertTrue(result.isAccountNonExpired());
        assertTrue(result.isAccountNonLocked());
        assertTrue(result.isCredentialsNonExpired());

        // Verify interactions
        verify(authUserRepository).existsByUsername(VALID_USERNAME);
        verify(authUserRepository).existsByEmail(VALID_EMAIL);
        verify(passwordEncoder).encode(VALID_PASSWORD);
        verify(authUserRepository).save(any(AuthUser.class));
    }

    @Test
    void registerNewUser_WithExistingUsername_ShouldThrowException() {
        // Arrange
        when(authUserRepository.existsByUsername(VALID_USERNAME)).thenReturn(true);

        // Act & Assert
        UsernameAlreadyExistsException exception = assertThrows(
                UsernameAlreadyExistsException.class,
                () -> userService.registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL)
        );

        // Assert exception message
        assertEquals("Username already exists: " + VALID_USERNAME, exception.getMessage());

        // Verify interactions
        verify(authUserRepository).existsByUsername(VALID_USERNAME);
        verify(authUserRepository, never()).existsByEmail(any());
        verify(authUserRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void registerNewUser_WithExistingEmail_ShouldThrowException() {
        // Arrange
        when(authUserRepository.existsByUsername(VALID_USERNAME)).thenReturn(false);
        when(authUserRepository.existsByEmail(VALID_EMAIL)).thenReturn(true);

        // Act & Assert
        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerNewUser(VALID_USERNAME, VALID_PASSWORD, VALID_EMAIL)
        );

        // Assert exception message
        assertEquals("Email already exists: " + VALID_EMAIL, exception.getMessage());

        // Verify interactions
        verify(authUserRepository).existsByUsername(VALID_USERNAME);
        verify(authUserRepository).existsByEmail(VALID_EMAIL);
        verify(authUserRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void loadUserByUsername_WithExistingUser_ShouldReturnUser() {
        // Arrange
        AuthUser existingUser = createValidUser();
        when(authUserRepository.findByUsername(VALID_USERNAME)).thenReturn(Optional.of(existingUser));

        // Act
        UserDetails result = userService.loadUserByUsername(VALID_USERNAME);

        // Assert
        assertNotNull(result);
        assertEquals(VALID_USERNAME, result.getUsername());
        verify(authUserRepository).findByUsername(VALID_USERNAME);
    }

    @Test
    void loadUserByUsername_WithNonExistentUser_ShouldThrowException() {
        // Arrange
        when(authUserRepository.findByUsername(VALID_USERNAME)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(VALID_USERNAME)
        );

        assertEquals("User not found: " + VALID_USERNAME, exception.getMessage());
        verify(authUserRepository).findByUsername(VALID_USERNAME);
    }

    @Test
    void findByUsername_WithExistingUser_ShouldReturnOptionalUser() {
        // Arrange
        AuthUser existingUser = createValidUser();
        when(authUserRepository.findByUsername(VALID_USERNAME)).thenReturn(Optional.of(existingUser));

        // Act
        Optional<AuthUser> result = userService.findByUsername(VALID_USERNAME);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(VALID_USERNAME, result.get().getUsername());
        verify(authUserRepository).findByUsername(VALID_USERNAME);
    }

    @Test
    void findByUsername_WithNonExistentUser_ShouldReturnEmptyOptional() {
        // Arrange
        when(authUserRepository.findByUsername(VALID_USERNAME)).thenReturn(Optional.empty());

        // Act
        Optional<AuthUser> result = userService.findByUsername(VALID_USERNAME);

        // Assert
        assertTrue(result.isEmpty());
        verify(authUserRepository).findByUsername(VALID_USERNAME);
    }

    // Helper method to create a valid user
    private AuthUser createValidUser() {
        return AuthUser.builder()
                .username(VALID_USERNAME)
                .password(ENCODED_PASSWORD)
                .email(VALID_EMAIL)
                .active(true)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }
}