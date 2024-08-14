package com.banyuijo.foundation.service.product.site.validator;

import com.banyuijo.foundation.dto.product.site.SiteProductCreateInput;
import com.banyuijo.foundation.dto.product.site.SiteProductEditInput;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.repository.SiteProductRepository;
import com.banyuijo.foundation.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SiteProductValidator {
    private final GlobalValidator validator;
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;

    public void validateRequest (SiteProductCreateInput createRequest, SiteProductEditInput editRequest){
        String siteProductName = Objects.isNull(editRequest) ? createRequest.getSiteProductName() : editRequest.getSiteProductName();
        String productTypeCode = Objects.isNull(editRequest) ? createRequest.getProductTypeCode() : editRequest.getProductTypeCode();
        validator.validateRequestMandatory(siteProductName);
        validator.validateRequestMandatory(productTypeCode);
        if (Objects.isNull(editRequest)){
            validator.validateRequestMandatory(createRequest.getSiteProductCode());
            validator.validateRequestLength(createRequest.getSiteProductCode(), 3, 3);
            if(siteProductRepository.existsBySiteProductCodeIgnoreCase(createRequest.getSiteProductCode())){
                throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product Code: " + createRequest.getSiteProductCode());
            }
        }
        if (!productTypeRepository.existsByProductTypeCode(productTypeCode)){
            throw new HttpStatusException(HttpStatusCode.INVALID_DATA_INPUT, "product Type: " + productTypeCode);
        }
        validator.validateRequestLength(siteProductName, 5, 15);
    }

    public void validateSiteProductId(UUID siteProductId){
        if (!siteProductRepository.existsById(siteProductId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "site product");
        }
    }
}
