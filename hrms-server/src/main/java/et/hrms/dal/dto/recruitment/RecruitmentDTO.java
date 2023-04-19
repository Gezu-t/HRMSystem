package et.hrms.dal.dto.recruitment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
public class RecruitmentDTO {

  private Long recruitmentId;
  private LocalDate applicationDate;
  private LocalDate interviewDate;
  private Boolean hired;
  private LocalDate hireDate;
  private InitiatorDTO initiatorDTO;
  private CandidateDTO candidateDTO;

}
