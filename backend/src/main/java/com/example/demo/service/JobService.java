package com.example.demo.service;


import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Job;
import com.example.demo.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobMapper jobMapper;

    public Job findJobById(Integer id){
        Job job = jobMapper.selectByJobId(id);
        return  job;

    }

    public List<Job> findPageWithResult(JobDTO jobDTO) {
        return jobMapper.findPageWithResult(jobDTO);
    }

    public Integer findPageWithCount(JobDTO jobDTO) {
        return jobMapper.findPageWithCount(jobDTO);
    }
}
