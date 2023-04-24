package et.hrms.dal.dto.recruitment;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDTO {
  private Long id;
  private String title;
  private String media;
  private LocalDate publishDate;
  private VacancyNoticeDTO vacancyNoticeDTO;


}
