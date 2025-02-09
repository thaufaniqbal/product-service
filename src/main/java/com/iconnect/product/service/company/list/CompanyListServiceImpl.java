package com.iconnect.product.service.company.list;

import com.iconnect.product.entity.company.Company;
import com.iconnect.product.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyListServiceImpl implements CompanyListService {
    private final CompanyRepository companyRepository;

    @Override
    public Object getCompanyList() {
        List<Company> results = companyRepository.findAll();
        return results;
    }
}
