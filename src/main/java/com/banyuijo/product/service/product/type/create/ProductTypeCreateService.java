package com.banyuijo.product.service.product.type.create;

import com.banyuijo.product.dto.product.type.ProductTypeCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductTypeCreateService {
    Object createProductType(ProductTypeCreateInput request, String loginId) throws JsonProcessingException;
}
