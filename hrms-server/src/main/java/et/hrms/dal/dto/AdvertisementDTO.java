package et.hrms.dal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdvertisementDTO {
  private Long id;
  private String title;
  private String media;
  private LocalDate publishDate;
  private VacancyNoticeDTO vacancyNoticeDTO;


}
