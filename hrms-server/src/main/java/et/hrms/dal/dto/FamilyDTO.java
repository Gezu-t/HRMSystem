package et.hrms.dal.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyDTO {
    private String nationality;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String dateOfBirth;
    private String gender;
    private String relationType;
    private String familyFirstName;
    private String familyLastName;


}
