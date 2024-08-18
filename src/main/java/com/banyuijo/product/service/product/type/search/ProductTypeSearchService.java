package com.banyuijo.product.service.product.type.search;

import com.banyuijo.product.dto.base.ResponsePageDTO;
import com.banyuijo.product.dto.product.type.ProductTypeSearchInput;

public interface ProductTypeSearchService {
    ResponsePageDTO searchProductType (ProductTypeSearchInput input);
}
