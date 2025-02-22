package com.gtltek.employee.core.dal.mapping;


import com.gtltek.employee.core.dal.dto.job.JobDTO;
import com.gtltek.employee.core.dal.model.job.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {


    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    Job toJob(JobDTO jobDTO);
    JobDTO toJobDTO(Job job);


}
