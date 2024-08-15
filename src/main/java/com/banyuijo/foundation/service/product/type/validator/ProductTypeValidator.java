package com.banyuijo.foundation.service.product.type.validator;

import com.banyuijo.foundation.dto.product.type.ProductTypeCreateInput;
import com.banyuijo.foundation.dto.product.type.ProductTypeEditInput;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductTypeValidator extends GlobalValidator  {
    private final ProductTypeRepository productTypeRepository;

    public void validateRequest(ProductTypeCreateInput createRequest, ProductTypeEditInput editRequest){
        String productTypeName = Objects.isNull(editRequest) ? createRequest.getProductTypeName() : editRequest.getProductTypeName();
        validateRequestMandatory(productTypeName);

        if (Objects.isNull(editRequest)){
            String productTypeCode = createRequest.getProductTypeCode() ;
            validateRequestMandatory(productTypeCode);
            validateRequestLength(productTypeCode, 3, 3);
            if(productTypeRepository.existsByProductTypeCode(createRequest.getProductTypeCode())){
                throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product type Code: " + productTypeCode);
            }
        }
        validateRequestLength(productTypeName, 5, 15);
    }
    public void validateProductTypeId(UUID productTypeId){
        if (!productTypeRepository.existsById(productTypeId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "Product Type");
        }
    }

}
