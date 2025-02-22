package com.gtltek.messaging.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auth_user")
@ToString(exclude = "password") // Prevent logging password accidentally
public class AuthUser implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = -3510262644261830410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    private boolean enabled = true;

    // NEW: Map the "active" column from the database.
    @Column(nullable = false)
    private boolean active = true;

    // Roles relationship
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Change to LAZY to improve performance
    @JoinTable(
            name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "auth_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Helper method to return role names.
     */
    public List<String> getRoleNames() {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    /** Optimized getAuthorities() method */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleNames().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
