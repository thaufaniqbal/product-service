package com.iconnect.product.service.company.delete;

import com.iconnect.product.dto.company.create.CompanyCreateInput;
import com.iconnect.product.service.company.validator.CompanyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyDeleteServiceImpl implements CompanyDeleteService {

    private final CompanyValidator validator;
    @Override
    public Object delete(CompanyCreateInput input) {
        return null;
    }
}
