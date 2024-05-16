package et.hrms.dal.dto;

import et.hrms.dal.model.GenderType;
import et.hrms.dal.model.CandidateType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
public class CandidateDTO {

  private Long candidateId;
  @NotNull(message = "Fist name is not null")
  @Max(150)
  @Min(2)
  private String firstName;
  @NotNull(message = "Last name must be specified")
  @Max(150)
  @Min(2)
  private String lastName;
  @NotNull(message = "Email must be specified")
  @Email(message = "Email must have to be specified")
  private String email;
  private String phoneNumber;
  private String address;
  private LocalDate registrationDate;
  private LocalDate dateOfBirth;
  private String experience;
  private String interview;
  private String levelOfEducation;
  private String mobile;
  private String officeTelephone;
  private String practical;
  private String recommendation;
  private String telResidence;
  private String batchCode;
  private GenderType genderType;
  private CandidateType candidateType;
  private boolean status;
  private String other;
  private String noPage;
  private String writtenExam;
  private String cgpa;

}
