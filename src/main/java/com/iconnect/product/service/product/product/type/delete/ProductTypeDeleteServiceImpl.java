package com.iconnect.product.service.product.product.type.delete;

import com.iconnect.product.entity.product.ProductType;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.repository.product.ProductTypeRepository;
import com.iconnect.product.service.product.product.type.validator.ProductTypeValidator;
import com.iconnect.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductTypeDeleteServiceImpl implements ProductTypeDeleteService {
    private final ProductTypeValidator validator;
    private final CustomLogger customLogger;
    private final ProductTypeRepository productTypeRepository;

    @Override
    public Object deleteProductType(UUID productTypeId, String loginId) throws JsonProcessingException {
        validator.validateProductTypeId(productTypeId);
        validator.validateExistProductWithProductType(productTypeId);
        ProductType productType = productTypeRepository.findByProductTypeId(productTypeId);
        productType.setDeleteStatus(BooleanStatus.YES.getCode());
        customLogger.setLogObject(productType, "deleteProductType", loginId);
        productTypeRepository.save(productType);
        return productType;
    }
}
