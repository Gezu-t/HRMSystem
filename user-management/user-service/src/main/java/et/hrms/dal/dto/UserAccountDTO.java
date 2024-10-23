package et.hrms.dal.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO {

    private Long id;
    private String username;
    private String email;
    private Boolean active;
    private Set<String> roles;
    private Instant createdAt;
    private Instant updatedAt;

}
