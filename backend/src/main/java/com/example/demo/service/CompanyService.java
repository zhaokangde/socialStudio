package com.example.demo.service;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Company;
import com.example.demo.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    public Company findCompanyById(Integer id){
        return companyMapper.selectByCompanyId(id);

    }

    public List<Company> findPageWithResult(CompanyDTO companyDTO){
        return companyMapper.findPageWithResult(companyDTO);
    }

    public Integer findPageWithCount(CompanyDTO companyDTO) {
        return companyMapper.findPageWithCount(companyDTO);
    }
}
