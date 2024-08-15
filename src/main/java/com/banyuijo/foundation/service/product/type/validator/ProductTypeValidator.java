package com.banyuijo.foundation.service.product.type.validator;

import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductTypeValidator {
    private final GlobalValidator validator;
    private final ProductTypeRepository productTypeRepository;
    public void validateProductTypeId(UUID siteProductId){
        if (!productTypeRepository.existsById(siteProductId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "site product");
        }
    }
}
