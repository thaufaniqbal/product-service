package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.site.SiteProductInput;
import com.banyuijo.foundation.entity.ProductType;
import com.banyuijo.foundation.entity.SiteProduct;
import com.banyuijo.foundation.enums.BooleanStatus;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.repository.SiteProductRepository;
import com.banyuijo.foundation.validator.GlobalValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductCreateServiceImpl implements SiteProductCreateService {

    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;
    private final GlobalValidator validator;

    @Override
    public Object createProduct(SiteProductInput request, String loginId) {
        validateRequest(request);
        SiteProduct siteProduct = build(request, loginId);
        saveProduct(siteProduct);
        return request;
    }
    private void validateRequest (SiteProductInput request){
        if (siteProductRepository.existsBySiteProductCodeIgnoreCase(request.getSiteProductCode())){
            throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, "Product Code: "+request.getSiteProductCode());
        }
        if (!productTypeRepository.existsByProductTypeCode(request.getProductTypeCode())){
            throw new HttpStatusException(HttpStatusCode.INVALID_DATA_INPUT, "product Type: " + request.getProductTypeCode());
        }
        validator.validateRequestLength(request.getSiteProductCode(), 3, 3);
        validator.validateRequestLength(request.getSiteProductName(), 5, 15);
    }
    @Transactional
    private void saveProduct(SiteProduct siteProduct){
        siteProductRepository.save(siteProduct);
    }

    private SiteProduct build (SiteProductInput request, String loginId){
        ProductType productType = productTypeRepository.findByProductTypeCode(request.getProductTypeCode());

        SiteProduct siteProduct = new SiteProduct();
        siteProduct.setSiteProductId(UUID.randomUUID());
        siteProduct.setSiteProductName(request.getSiteProductName());
        siteProduct.setSiteProductCode(request.getSiteProductCode());
        siteProduct.setProductTypeId(productType.getProductTypeId());
        siteProduct.setCreatedBy(loginId);
        siteProduct.setCreatedDate(LocalDateTime.now());
        siteProduct.setLastUpdatedBy(loginId);
        siteProduct.setLastUpdatedDate(LocalDateTime.now());
        siteProduct.setDeleteStatus(BooleanStatus.NO.getCode());
        return siteProduct;
    }

}
