package com.iconnect.product.service.company.edit;

import com.iconnect.product.dto.company.create.CompanyCreateInput;
import com.iconnect.product.service.company.validator.CompanyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyEditServiceImpl implements CompanyEditService {

    private final CompanyValidator validator;
    @Override
    public Object edit(CompanyCreateInput input) {
        return null;
    }
}
