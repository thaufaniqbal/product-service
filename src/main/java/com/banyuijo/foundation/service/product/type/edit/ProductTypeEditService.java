package com.banyuijo.foundation.service.product.type.edit;

import com.banyuijo.foundation.dto.product.type.ProductTypeEditInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface ProductTypeEditService {
    Object editProductType(ProductTypeEditInput request, String loginId, UUID productTypeId) throws JsonProcessingException;

}
