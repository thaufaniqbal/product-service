package com.iconnect.product.service.product.type.create;

import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.entity.ProductType;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.repository.ProductTypeRepository;
import com.iconnect.product.service.product.type.validator.ProductTypeValidator;
import com.iconnect.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductTypeCreateServiceImpl implements ProductTypeCreateService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeValidator validator;
    private final CustomLogger customLogger;
    @Override
    public Object createProductType(ProductTypeCreateInput request, String loginId) throws JsonProcessingException {
        validator.validateRequest(request, null);
        ProductType productType = build(request, loginId);
        customLogger.setLogObject(productType, "createProductType", loginId);
        saveProduct(productType);
        return request;
    }
    @Transactional
    private void saveProduct(ProductType productType){
        productTypeRepository.save(productType);
    }
    private ProductType build (ProductTypeCreateInput request, String loginId){
        ProductType output = new ProductType();
        output.setProductTypeId(UUID.randomUUID());
        output.setProductTypeCode(request.getProductTypeCode());
        output.setProductTypeName(request.getProductTypeName());
        output.setCreatedBy(loginId);
        output.setCreatedDate(LocalDateTime.now());
        output.setLastUpdatedBy(loginId);
        output.setLastUpdatedDate(LocalDateTime.now());
        output.setDeleteStatus(BooleanStatus.NO.getCode());
        return output;
    }
}
