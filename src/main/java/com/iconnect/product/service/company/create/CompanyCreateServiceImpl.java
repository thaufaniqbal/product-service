package com.iconnect.product.service.company.create;

import com.iconnect.product.dto.company.create.CompanyCreateInput;
import com.iconnect.product.entity.company.Company;
import com.iconnect.product.repository.company.CompanyRepository;
import com.iconnect.product.service.company.validator.CompanyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyCreateServiceImpl implements CompanyCreateService {
    private final CompanyRepository companyRepository;
    private final CompanyValidator validator;
    @Override
    public Object create(CompanyCreateInput input, UUID companyId) {
        Company company = build(input, companyId);
        companyRepository.save(company);
        return company;
    }

    private Company build (CompanyCreateInput input, UUID companyId){
        Company result = new Company();
        result.setCompanyId(Objects.isNull(companyId) ? UUID.randomUUID() : companyId);
        return build (input, result);
    }
    private Company build (CompanyCreateInput input, Company result){
        result.setCompanyName(input.getCompanyName());
        result.setCreatedDate(LocalDateTime.now());
        result.setCreatedBy("system v1.0.0");
        return result;
    }

}
