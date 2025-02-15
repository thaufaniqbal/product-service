package com.iconnect.product.service.product.product.site.validator;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductEditInput;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.product.ProductTypeRepository;
import com.iconnect.product.repository.product.SiteProductRepository;
import com.iconnect.product.validator.GlobalValidator;
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
        UUID productTypeId = Objects.isNull(editRequest) ? createRequest.getProductTypeId() : editRequest.getProductTypeId();
        validateRequestMandatory(siteProductName, HttpStatusCode.MISSING_MANDATORY_PROPERTY, "siteProductName");
        validateRequestMandatory(productTypeId, HttpStatusCode.MISSING_MANDATORY_PROPERTY, "productTypeId");
        if (Objects.isNull(editRequest)){
            validateRequestMandatory(createRequest.getSiteProductCode(), HttpStatusCode.MISSING_MANDATORY_PROPERTY);
            validateRequestLength(createRequest.getSiteProductCode(), 1, 10, HttpStatusCode.MINIMUM_LENGTH_EXCEEDED,  HttpStatusCode.MAXIMUM_LENGTH_EXCEEDED);
            if(siteProductRepository.existsBySiteProductCodeIgnoreCaseAndDeleteStatus(createRequest.getSiteProductCode(), BooleanStatus.NO.getCode())){
                throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product Code: " + createRequest.getSiteProductCode());
            }
            if (!productTypeRepository.existsByProductTypeId(createRequest.getProductTypeId())){
                throw new HttpStatusException(HttpStatusCode.INVALID_DATA_INPUT, "product Type: " + productTypeId);
            }
        }
        validateRequestLength(siteProductName, 5, 30, HttpStatusCode.MINIMUM_LENGTH_EXCEEDED,  HttpStatusCode.MAXIMUM_LENGTH_EXCEEDED);
    }

    public void validateSiteProductId(UUID siteProductId){
        if (!siteProductRepository.existsById(siteProductId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "site product");
        }
    }
}
