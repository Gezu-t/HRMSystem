package et.hrms.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VacancyNoticeDTO that)) return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getJobTitle(), that.getJobTitle());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle);
        // add more fields as needed
    }

}
