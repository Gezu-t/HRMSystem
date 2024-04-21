package et.hrms.dal.dto.education;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class EducationLevelDTO {
  private Long educationLevelId;
  @NotNull(message = "Education level name must not be null values")
  @Max(254)
  @Min(2)
  private String educationLevelName;
}
