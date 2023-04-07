package et.hrms.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyNoticeDTO {
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private Integer numberOfPositions;
    private LocalDate publishDate;
    private LocalDate closeDate;
    private AdvertisementDTO advertisement;

}
