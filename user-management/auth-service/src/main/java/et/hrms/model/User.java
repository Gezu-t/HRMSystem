package et.hrms.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Entity
@Table(name = "users")
public class User implements org.springframework.security.core.userdetails.UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private String email;

    // Implement methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return user roles as authorities
        return null; // Implement as needed
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    // Implement other methods...
}
