package com.iconnect.product.service.product.type.delete;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface ProductTypeDeleteService {
    Object deleteProductType(UUID productTypeId, String loginId) throws JsonProcessingException;
}
