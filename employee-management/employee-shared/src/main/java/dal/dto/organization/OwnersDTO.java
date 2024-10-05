package dal.dto.organization;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnersDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthPlace;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String nationality;
    private String idNumber;
    private String role;
    private String email;
    private String phoneNumber;
    private OrganizationDTO organizationDTO;
}
