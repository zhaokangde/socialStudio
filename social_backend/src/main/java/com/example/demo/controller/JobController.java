package com.example.demo.controller;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.JobDTO;
import com.example.demo.entity.Company;
import com.example.demo.entity.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/findJobById/{id}")
    public Job findIobById(@PathVariable("id") Integer id){
        return jobService.findJobById(id);
    }

    @PostMapping(path="/findPageWithResult" )
    public List<Job> findPageWithResult(@RequestBody JobDTO jobDTO){
        return jobService.findPageWithResult(jobDTO);
    }

    @PostMapping(path="/findPageWithCount" )
    public Integer findPageWithCount(@RequestBody JobDTO jobDTO){
        return jobService.findPageWithCount(jobDTO);
    }
}
