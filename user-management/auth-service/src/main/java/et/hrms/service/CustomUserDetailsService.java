package et.hrms.service;

import et.hrms.model.AuthUser;
import et.hrms.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user by username from the repository
        AuthUser authUser = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Map the AuthUser to Spring Security's UserDetails
        return User.builder()
                .username(authUser.getUsername())
                .password(authUser.getPassword()) // Use the password stored in AuthUser
                .authorities(authUser.getAuthorities()) // Map roles/authorities
                .accountExpired(!authUser.isAccountNonExpired()) // Check if the account is expired
                .accountLocked(!authUser.isAccountNonLocked()) // Check if the account is locked
                .credentialsExpired(!authUser.isCredentialsNonExpired()) // Check if credentials are expired
                .disabled(!authUser.isEnabled()) // Check if the user is active
                .build();
    }


}
