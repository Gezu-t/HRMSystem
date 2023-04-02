package et.hrms.dal.mapping;

import et.hrms.dal.dto.VacancyNoticeDTO;
import et.hrms.dal.model.VacancyNotice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacancyNoticeMapper {

    VacancyNoticeMapper INSTANCE = Mappers.getMapper(VacancyNoticeMapper.class);

    VacancyNoticeDTO toDto(VacancyNotice vacancyNotice);
    VacancyNotice toEntity(VacancyNoticeDTO vacancyNoticeDTO);
    List<VacancyNoticeDTO> toDtoList(List<VacancyNotice> vacancyNotices);
    List<VacancyNotice> toEntityList(List<VacancyNoticeDTO> vacancyNoticeDTOs);
}
