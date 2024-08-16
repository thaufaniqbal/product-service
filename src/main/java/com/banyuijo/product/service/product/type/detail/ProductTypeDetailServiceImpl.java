package com.banyuijo.product.service.product.type.detail;

import com.banyuijo.product.dto.product.type.ProductTypeDetailOutput;
import com.banyuijo.product.entity.ProductType;
import com.banyuijo.product.enums.HttpStatusCode;
import com.banyuijo.product.exception.HttpStatusException;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.service.product.type.validator.ProductTypeValidator;
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
