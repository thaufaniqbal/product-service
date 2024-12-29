package com.iconnect.product.service.product.site.validator;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductEditInput;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.ProductTypeRepository;
import com.iconnect.product.repository.SiteProductRepository;
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
        validateRequestMandatory(siteProductName);
        validateRequestMandatory(productTypeId, "productTypeId");
        if (Objects.isNull(editRequest)){
            validateRequestMandatory(createRequest.getSiteProductCode());
            validateRequestLength(createRequest.getSiteProductCode(), 1, 3);
            if(siteProductRepository.existsBySiteProductCodeIgnoreCaseAndDeleteStatus(createRequest.getSiteProductCode(), BooleanStatus.NO.getCode())){
                throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product Code: " + createRequest.getSiteProductCode());
            }
            if (!productTypeRepository.existsByProductTypeId(createRequest.getProductTypeId())){
                throw new HttpStatusException(HttpStatusCode.INVALID_DATA_INPUT, "product Type: " + productTypeId);
            }
        }
        validateRequestLength(siteProductName, 5, 15);
    }

    public void validateSiteProductId(UUID siteProductId){
        if (!siteProductRepository.existsById(siteProductId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND_FOR, "site product");
        }
    }
}
