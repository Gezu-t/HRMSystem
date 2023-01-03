package et.hrms.dal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {


    private String organizationName;
    private String address;
    private String establishmentDate;
}
