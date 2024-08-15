package com.banyuijo.foundation.service.product.site.edit;

import com.banyuijo.foundation.dto.product.site.SiteProductEditInput;
import com.banyuijo.foundation.entity.ProductType;
import com.banyuijo.foundation.entity.SiteProduct;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.repository.SiteProductRepository;
import com.banyuijo.foundation.service.product.site.validator.SiteProductValidator;
import com.banyuijo.foundation.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductEditServiceImpl implements SiteProductEditService {
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SiteProductValidator validator;
    private final CustomLogger customLogger;
    @Override
    public Object editSiteProduct(SiteProductEditInput request, String loginId, UUID siteProductId) throws JsonProcessingException {
        validator.validateRequest(null, request);
        validator.validateSiteProductId(siteProductId);
        SiteProduct siteProduct = build(request, loginId, siteProductId);
        customLogger.setLogObject(siteProduct);
        saveProduct(siteProduct);
        return request;
    }
    @Transactional
    private void saveProduct(SiteProduct siteProduct){
        siteProductRepository.save(siteProduct);
    }
    private SiteProduct build (SiteProductEditInput request, String loginId, UUID siteProductId){
        ProductType productType = productTypeRepository.findByProductTypeCode(request.getProductTypeCode());
        SiteProduct siteProduct = siteProductRepository.findBySiteProductId(siteProductId);
        siteProduct.setSiteProductName(request.getSiteProductName());
        siteProduct.setProductTypeId(productType.getProductTypeId());
        siteProduct.setLastUpdatedBy(loginId);
        siteProduct.setLastUpdatedDate(LocalDateTime.now());
        return siteProduct;
    }
}
