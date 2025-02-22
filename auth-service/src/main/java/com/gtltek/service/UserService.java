package com.gtltek.service;

import com.gtltek.messaging.model.AuthUser;
import com.gtltek.messaging.repository.AuthUserRepository;
import com.gtltek.exception.EmailAlreadyExistsException;
import com.gtltek.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Transactional(readOnly = true)
    public Optional<AuthUser> findByUsername(String username) {
        return authUserRepository.findByUsername(username);
    }

    @Transactional
    public AuthUser registerNewUser(String username, String password, String email) {
        if (authUserRepository.existsByUsername(username)) {
            logger.warn("Registration attempt with existing username: {}", username);
            throw new UsernameAlreadyExistsException("Username already exists: " + username);
        }

        if (authUserRepository.existsByEmail(email)) {
            logger.warn("Registration attempt with existing email: {}", email);
            throw new EmailAlreadyExistsException("Email already exists: " + email);
        }

        AuthUser user = AuthUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // Hash the password
                .email(email)
                .active(true)                        // Set active to true
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();


        return authUserRepository.save(user);
    }
}
