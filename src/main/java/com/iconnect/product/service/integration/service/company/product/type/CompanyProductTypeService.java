package com.iconnect.product.service.integration.service.company.product.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;

import java.util.UUID;

public interface CompanyProductTypeService {
    Object createProductTypeByCompany (UUID userId, String loginId, ProductTypeCreateInput input) throws JsonProcessingException;
    Object getProductTypeList (UUID userId);
    Object searchProductType (UUID userId, ProductTypeSearchInput input);

}
