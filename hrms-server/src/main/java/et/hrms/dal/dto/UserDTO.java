package et.hrms.dal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    @NotNull(message = "user name must not be null")
    @Max(150)
    @Min(2)
    private String username;
    @NotNull(message = "Password must not be null")
    @Max(200)
    @Min(8)
    private String password;
}
