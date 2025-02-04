package com.iconnect.product.service.product.product.type.search;

import com.iconnect.product.dto.base.ResponsePageDTO;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;

public interface ProductTypeSearchService {
    ResponsePageDTO searchProductType (ProductTypeSearchInput input);
}
