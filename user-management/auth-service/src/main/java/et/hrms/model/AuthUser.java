package et.hrms.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_user")
public class AuthUser implements UserDetails {

    @Serial
    private static final long serialVersionUID = -3510262644261830410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)  // Assuming roles are stored as strings
    @CollectionTable(name = "auth_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    // Implement methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Map roles to GrantedAuthority objects
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // You can customize this based on user data
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Return true if account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // You can customize this
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
