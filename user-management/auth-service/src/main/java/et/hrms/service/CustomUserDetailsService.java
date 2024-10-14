package et.hrms.service;

import et.hrms.client.UserServiceClient;
import et.hrms.dal.dto.UserResponseDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceClient userServiceClient;

    public CustomUserDetailsService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserResponseDTO userDto = userServiceClient.getUserByUsername(username);
            return new User(
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.isActive(),
                    true, // accountNonExpired
                    true, // credentialsNonExpired
                    true, // accountNonLocked
                    userDto.getRoles().stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found", e);
        }
    }
}