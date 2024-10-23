package et.hrms.dal.dto;


import lombok.*;

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
    private Set<String> roles;
    private Instant createdAt;
    private Instant updatedAt;
}