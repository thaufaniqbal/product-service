package com.banyuijo.product.service.product.type.edit;

import com.banyuijo.product.dto.product.type.ProductTypeEditInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface ProductTypeEditService {
    Object editProductType(ProductTypeEditInput request, String loginId, UUID productTypeId) throws JsonProcessingException;

}
