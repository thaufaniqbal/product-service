package com.iconnect.product.service.integration.company.product.type;

import com.iconnect.product.dto.product.type.ProductTypeCreateInput;

import java.util.UUID;

public interface CompanyProductTypeService {
    Object createProductTypeByCompany (UUID userId, String loginId, ProductTypeCreateInput input);

}
