package et.hrms.controller.recruitment;

import et.hrms.dal.dto.recruitment.VacancyNoticeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface VacancyNoticeController {
  @GetMapping
  ResponseEntity<List<VacancyNoticeDTO>> getAllVacancyNotices();

  @GetMapping("/{id}")
  ResponseEntity<VacancyNoticeDTO> getVacancyNoticeById(@PathVariable Long id);

  @PostMapping
  ResponseEntity<VacancyNoticeDTO> createVacancyNotice(@RequestBody VacancyNoticeDTO vacancyNoticeDTO);

  @PutMapping("/{id}")
  ResponseEntity<VacancyNoticeDTO> updateVacancyNotice(@PathVariable Long id, @RequestBody VacancyNoticeDTO vacancyNoticeDTO);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteVacancyNotice(@PathVariable Long id);
}
