package com.iconnect.product.service.integration.company.product.type;

import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;

import java.util.UUID;

public interface CompanyProductTypeService {
    Object createProductTypeByCompany (UUID userId, String loginId, ProductTypeCreateInput input);
    Object getProductTypeList (UUID userId);
    Object searchProductType (UUID userId, ProductTypeSearchInput input);

}
