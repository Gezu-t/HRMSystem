package com.gtltek.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
