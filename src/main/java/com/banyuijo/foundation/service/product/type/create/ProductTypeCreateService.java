package com.banyuijo.foundation.service.product.type.create;

import com.banyuijo.foundation.dto.product.type.ProductTypeCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductTypeCreateService {
    Object createProductType(ProductTypeCreateInput request, String loginId) throws JsonProcessingException;
}
