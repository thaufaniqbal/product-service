package com.iconnect.product.service.product.type.detail;

import com.iconnect.product.dto.product.type.ProductTypeDetailOutput;
import com.iconnect.product.entity.ProductType;
import com.iconnect.product.repository.ProductTypeRepository;
import com.iconnect.product.service.product.type.validator.ProductTypeValidator;
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
        ProductType productType = productTypeRepository.findByProductTypeId(productTypeId);
        return objectMapper.convertValue(productType, ProductTypeDetailOutput.class);
    }
}
