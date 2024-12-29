package com.banyuijo.product.service.product.type.validator;

import com.banyuijo.product.dto.product.type.ProductTypeCreateInput;
import com.banyuijo.product.dto.product.type.ProductTypeEditInput;
import com.banyuijo.product.entity.SiteProduct;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.enums.HttpStatusCode;
import com.banyuijo.product.exception.HttpStatusException;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.repository.SiteProductRepository;
import com.banyuijo.product.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductTypeValidator extends GlobalValidator  {
    private final ProductTypeRepository productTypeRepository;
    private final SiteProductRepository productRepository;

    public void validateRequest(ProductTypeCreateInput createRequest, ProductTypeEditInput editRequest){
        String productTypeName = Objects.isNull(editRequest) ? createRequest.getProductTypeName() : editRequest.getProductTypeName();
        validateRequestMandatory(productTypeName);

        if (Objects.isNull(editRequest)){
            String productTypeCode = createRequest.getProductTypeCode() ;
            validateRequestMandatory(productTypeCode);
            validateRequestLength(productTypeCode, 3, 3);
            if(Boolean.TRUE.equals(productTypeRepository.existsByProductTypeCode(createRequest.getProductTypeCode()))){
                throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product type Code: " + productTypeCode);
            }
        }
        validateRequestLength(productTypeName, 5, 15);
    }
    public void validateProductTypeId(UUID productTypeId){
        if (Boolean.FALSE.equals(productTypeRepository.existsByProductTypeId(productTypeId))){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "Product Type");
        }
    }

    public void validateExistProductWithProductType (UUID productTypeId){
        List<SiteProduct> siteProducts = productRepository.findAllByProductTypeIdAndDeleteStatus(productTypeId, BooleanStatus.NO.getCode());
        if (!siteProducts.isEmpty()){
            throw new HttpStatusException(HttpStatusCode.FAILED_DELETE, siteProducts.size(),"Product Type",
                    ", productCode:" + siteProducts.stream().map(SiteProduct::getSiteProductCode).collect(Collectors.toList()));
        }
    }
}
