package et.hrms.dal.mapping;


import et.hrms.dal.dto.JobDTO;
import et.hrms.dal.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {


    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);


    Job toJob(JobDTO jobDTO);
    JobDTO toJobDTO(Job job);



}
