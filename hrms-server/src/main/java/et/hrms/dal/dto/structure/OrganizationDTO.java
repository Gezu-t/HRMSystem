package et.hrms.dal.dto.structure;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Data
public class OrganizationDTO {

    private Long organizationId;
    @NotNull
    @UniqueElements(message = "duplicate organization name exist")
    private String organizationName;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate establishmentDate;
    @NotNull
    private String ownerName;

    @NotNull
    private OrganizationAddressDTO organizationAddressDTO;
}
