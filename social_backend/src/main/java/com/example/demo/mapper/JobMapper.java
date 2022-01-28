package com.example.demo.mapper;

import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Job;
import java.util.List;

public interface JobMapper {


    Job selectByJobId(Integer jobId);

    List<Job> findPageWithResult(JobDTO jobDTO);

    Integer findPageWithCount(JobDTO jobDTO);
}