package et.hrms.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppearanceDTO {

    private Long appearanceId;
    private String height;
    private String weight;
    private String hairColor;
    private String eyeColor;
    private String skinColor;
    private String bloodGroup;


}
