package et.hrms.service.impl;

import et.hrms.dal.mapping.VacancyNoticeMapper;
import et.hrms.dal.repository.VacancyNoticeRepository;
import et.hrms.service.VacancyNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyNoticeServiceImpl implements VacancyNoticeService {

    private final VacancyNoticeRepository vacancyNoticeRepository;
    private final VacancyNoticeMapper vacancyNoticeMapper;
}
