package et.hrms.dal.dto;

import java.time.LocalDate;

public class VacancyNoticeDTO {
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private Integer numberOfPositions;
    private LocalDate publishDate;
    private LocalDate closeDate;
    private AdvertisementDTO advertisement;

}
