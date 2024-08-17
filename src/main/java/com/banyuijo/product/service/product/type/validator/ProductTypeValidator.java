package com.banyuijo.product.service.product.type.validator;

import com.banyuijo.product.dto.product.type.ProductTypeCreateInput;
import com.banyuijo.product.dto.product.type.ProductTypeEditInput;
import com.banyuijo.product.enums.HttpStatusCode;
import com.banyuijo.product.exception.HttpStatusException;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.validator.GlobalValidator;
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
        if (!productTypeRepository.existsByProductTypeId(productTypeId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "Product Type");
        }
    }

}
