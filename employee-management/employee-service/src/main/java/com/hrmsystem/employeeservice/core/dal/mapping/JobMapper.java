package com.hrmsystem.employeeservice.core.dal.mapping;


import dal.dto.job.JobDTO;
import dal.model.job.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {


    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    Job toJob(JobDTO jobDTO);
    JobDTO toJobDTO(Job job);


}
