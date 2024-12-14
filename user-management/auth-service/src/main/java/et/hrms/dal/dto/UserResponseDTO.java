package et.hrms.dal.dto;


import et.hrms.dal.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Boolean active;
    private Set<Role> roles;
    private Instant createdAt;
    private Instant updatedAt;
}