package et.hrms.dal.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {



    @NotEmpty
    @NonNull
    private Long OrganizationId;
    private String organizationName;
    private String address;
    private String establishmentDate;

    private String owner;


}
