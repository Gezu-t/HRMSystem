package et.hrms.dal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacancyNoticeDTO {
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private Integer numberOfPositions;
    private LocalDate publishDate;
    private LocalDate closeDate;
    private AdvertisementDTO advertisement;

}
