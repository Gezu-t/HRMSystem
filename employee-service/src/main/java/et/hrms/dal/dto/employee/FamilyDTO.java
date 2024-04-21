package et.hrms.dal.dto.employee;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyDTO {
    private Long familyId;
    private String nationality;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String gender;
    private String relationType;
    private String familyFirstName;
    private String familyLastName;

    private Long employeeId;


}
