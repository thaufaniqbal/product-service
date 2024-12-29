package com.banyuijo.product.service.product.site.product.edit;

import com.banyuijo.product.dto.product.site.product.SiteProductEditInput;
import com.banyuijo.product.entity.ProductType;
import com.banyuijo.product.entity.SiteProduct;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.repository.SiteProductRepository;
import com.banyuijo.product.service.product.site.validator.SiteProductValidator;
import com.banyuijo.product.util.CustomLogger;
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
        customLogger.setLogObject(siteProduct, "editSiteProduct", loginId);
        saveProduct(siteProduct);
        return request;
    }
    @Transactional
    private void saveProduct(SiteProduct siteProduct){
        siteProductRepository.save(siteProduct);
    }
    private SiteProduct build (SiteProductEditInput request, String loginId, UUID siteProductId){
        ProductType productType = productTypeRepository.findByProductTypeId(request.getProductTypeId());
        SiteProduct siteProduct = siteProductRepository.findBySiteProductId(siteProductId);
        siteProduct.setSiteProductName(request.getSiteProductName());
        siteProduct.setProductTypeId(productType.getProductTypeId());
        siteProduct.setLastUpdatedBy(loginId);
        siteProduct.setLastUpdatedDate(LocalDateTime.now());
        return siteProduct;
    }
}
