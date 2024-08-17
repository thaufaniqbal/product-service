package com.banyuijo.product.service.product.site.validator;

import com.banyuijo.product.dto.product.site.SiteProductCreateInput;
import com.banyuijo.product.dto.product.site.SiteProductEditInput;
import com.banyuijo.product.enums.HttpStatusCode;
import com.banyuijo.product.exception.HttpStatusException;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.repository.SiteProductRepository;
import com.banyuijo.product.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SiteProductValidator extends GlobalValidator {
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;

    public void validateRequest (SiteProductCreateInput createRequest, SiteProductEditInput editRequest){
        String siteProductName = Objects.isNull(editRequest) ? createRequest.getSiteProductName() : editRequest.getSiteProductName();
        String productTypeCode = Objects.isNull(editRequest) ? createRequest.getProductTypeCode() : editRequest.getProductTypeCode();
        validateRequestMandatory(siteProductName);
        validateRequestMandatory(productTypeCode);
        if (Objects.isNull(editRequest)){
            validateRequestMandatory(createRequest.getSiteProductCode());
            validateRequestLength(createRequest.getSiteProductCode(), 1, 3);
            if(siteProductRepository.existsBySiteProductCodeIgnoreCase(createRequest.getSiteProductCode())){
                throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product Code: " + createRequest.getSiteProductCode());
            }
        }
        if (!productTypeRepository.existsByProductTypeCode(productTypeCode)){
            throw new HttpStatusException(HttpStatusCode.INVALID_DATA_INPUT, "product Type: " + productTypeCode);
        }
        validateRequestLength(siteProductName, 5, 15);
    }

    public void validateSiteProductId(UUID siteProductId){
        if (!siteProductRepository.existsById(siteProductId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "site product");
        }
    }
}
