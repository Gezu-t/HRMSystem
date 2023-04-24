package et.hrms.service.recruitment;

import et.hrms.dal.dto.recruitment.VacancyNoticeDTO;

import java.util.List;

public interface VacancyNoticeService {
    List<VacancyNoticeDTO> findAll();
    VacancyNoticeDTO findById(Long id);
    VacancyNoticeDTO save(VacancyNoticeDTO vacancyNotice);
    void deleteById(Long id);


}
