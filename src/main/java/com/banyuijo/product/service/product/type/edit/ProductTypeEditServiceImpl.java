package com.banyuijo.product.service.product.type.edit;

import com.banyuijo.product.dto.product.type.ProductTypeEditInput;
import com.banyuijo.product.entity.ProductType;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.service.product.type.validator.ProductTypeValidator;
import com.banyuijo.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductTypeEditServiceImpl implements ProductTypeEditService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeValidator validator;
    private final CustomLogger customLogger;
    @Override
    public Object editProductType(ProductTypeEditInput request, String loginId, UUID productTypeId) throws JsonProcessingException {
        validator.validateRequest(null, request);
        validator.validateProductTypeId(productTypeId);
        ProductType productType = build(request, loginId, productTypeId);
        customLogger.setLogObject(productType, "editProductType", loginId);
        saveProduct(productType);
        return request;
    }
    @Transactional
    private void saveProduct(ProductType productType){
        productTypeRepository.save(productType);
    }
    private ProductType build (ProductTypeEditInput request, String loginId, UUID productTypeId){
        ProductType output = productTypeRepository.findByProductTypeId(productTypeId);
        output.setProductTypeName(request.getProductTypeName());
        output.setLastUpdatedBy(loginId);
        output.setLastUpdatedDate(LocalDateTime.now());
        return output;
    }
}
