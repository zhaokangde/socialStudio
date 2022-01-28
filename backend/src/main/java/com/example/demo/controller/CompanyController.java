package com.example.demo.controller;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Company;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/findCompanyById/{id}")
    public Company findCompanyById(@PathVariable("id") Integer id){
        return companyService.findCompanyById(id);
    }

    @PostMapping(path="/findPageWithResult" )
    public List<Company> findPageWithResult(@RequestBody CompanyDTO companyDTO){
        return companyService.findPageWithResult(companyDTO);
    }

    @PostMapping(path="/findPageWithCount" )
    public Integer findPageWithCount(@RequestBody CompanyDTO companyDTO){
        return companyService.findPageWithCount(companyDTO);

    }

    @RequestMapping(path="/hello" )
    public String hello(){
        return "做大做强";

    }

}
