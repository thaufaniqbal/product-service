package com.iconnect.product.service.integration.company.product.type;

import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CompanyProductTypeServiceImpl implements CompanyProductTypeService {
    @Override
    public Object createProductTypeByCompany(UUID userId, String loginId, ProductTypeCreateInput input) {
        return null;
    }

    @Override
    public Object getProductTypeList(UUID userId) {
        return null;
    }

    @Override
    public Object searchProductType(UUID userId, ProductTypeSearchInput input) {
        return null;
    }
}
