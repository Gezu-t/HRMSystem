package com.hrmsystem.employeeprofileservice.dal.mapping;


import com.hrmsystem.employeeprofileservice.dal.dto.job.JobDTO;
import com.hrmsystem.employeeservice.core.dal.model.job.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {


    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);


    Job toJob(JobDTO jobDTO);
    JobDTO toJobDTO(Job job);



}
