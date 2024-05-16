package et.hrms.dal.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class EducationDetailDTO {
  private Long educationDetailId;
  private Long educationId;
  private String degree;
  private String major;
  private String minor;
  private String grade;
  private String gradePoint;
  private String gpa;
  private String gpaOutOf;
  private String gpaOutOfScale;
  private Year academicYear;
  private Long educationTypeId;


}
