package com.iconnect.product.service.integration.company.product.type;

import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CompanyProductTypeServiceImpl implements CompanyProductTypeService {
    @Override
    public Object createProductTypeByCompany(UUID userId, ProductTypeCreateInput input) {
        return null;
    }
}
