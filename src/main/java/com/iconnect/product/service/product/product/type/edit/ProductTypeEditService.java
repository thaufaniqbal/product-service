package com.iconnect.product.service.product.product.type.edit;

import com.iconnect.product.dto.product.type.ProductTypeEditInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface ProductTypeEditService {
    Object editProductType(ProductTypeEditInput request, String loginId, UUID productTypeId) throws JsonProcessingException;

}
