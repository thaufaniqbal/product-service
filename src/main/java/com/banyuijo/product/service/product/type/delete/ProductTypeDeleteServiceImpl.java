package com.banyuijo.product.service.product.type.delete;

import com.banyuijo.product.entity.ProductType;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.service.product.type.validator.ProductTypeValidator;
import com.banyuijo.product.util.CustomLogger;
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
