package com.iconnect.product.service.company.create;

import com.iconnect.product.dto.company.create.CompanyCreateInput;
import com.iconnect.product.service.company.validator.CompanyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyCreateServiceImpl implements CompanyCreateService {

    private final CompanyValidator validator;
    @Override
    public Object create(CompanyCreateInput input, UUID companyId) {
        return null;
    }
}
