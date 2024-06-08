package et.hrms.dal.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@AllArgsConstructor@NoArgsConstructor
public class EducationTypeDTO {
  private Long educationTypeId;
  private String name;

  public Long getEducationTypeId() {
    return educationTypeId;
  }

  public void setEducationTypeId(Long educationTypeId) {
    this.educationTypeId = educationTypeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
