package com.hrmsystem.employeeservice.core.dal.mapping;

import dal.dto.job.JobDTO;
import dal.model.job.Job;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T13:21:39+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public Job toJob(JobDTO jobDTO) {
        if ( jobDTO == null ) {
            return null;
        }

        Job job = new Job();

        job.setTitle( jobDTO.getTitle() );
        job.setMaxSalary( jobDTO.getMaxSalary() );
        job.setMinSalary( jobDTO.getMinSalary() );
        job.setDescription( jobDTO.getDescription() );

        return job;
    }

    @Override
    public JobDTO toJobDTO(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDTO jobDTO = new JobDTO();

        jobDTO.setTitle( job.getTitle() );
        jobDTO.setMaxSalary( job.getMaxSalary() );
        jobDTO.setMinSalary( job.getMinSalary() );
        jobDTO.setDescription( job.getDescription() );

        return jobDTO;
    }
}
