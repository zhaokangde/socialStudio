package com.example.demo.mapper;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.entity.Company;
import java.util.List;

public interface CompanyMapper {

    Company selectByCompanyId(Integer companyId);

    List<Company> findPageWithResult(CompanyDTO companyDTO);

    Integer findPageWithCount(CompanyDTO companyDTO);


}