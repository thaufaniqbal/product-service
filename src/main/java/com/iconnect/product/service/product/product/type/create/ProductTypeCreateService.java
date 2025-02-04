package com.iconnect.product.service.product.product.type.create;

import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductTypeCreateService {
    Object createProductType(ProductTypeCreateInput request, String loginId) throws JsonProcessingException;
}
