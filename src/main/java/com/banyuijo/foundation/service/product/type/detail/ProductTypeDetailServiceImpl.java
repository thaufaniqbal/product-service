package com.banyuijo.foundation.service.product.type.detail;

import com.banyuijo.foundation.dto.product.type.ProductTypeDetailOutput;
import com.banyuijo.foundation.entity.ProductType;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.service.product.type.validator.ProductTypeValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductTypeDetailServiceImpl implements ProductTypeDetailService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeValidator validator;
    private final ObjectMapper objectMapper;
    @Override
    public ProductTypeDetailOutput getProductTypeDetail(UUID productTypeId) {
        validator.validateProductTypeId(productTypeId);
        ProductType productType = productTypeRepository.findById(productTypeId)
                .orElseThrow(()->new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND));
        return objectMapper.convertValue(productType, ProductTypeDetailOutput.class);
    }
}
