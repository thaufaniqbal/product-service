package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.site.SiteProductCreateInput;
import com.banyuijo.foundation.entity.ProductType;
import com.banyuijo.foundation.entity.SiteProduct;
import com.banyuijo.foundation.enums.BooleanStatus;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.repository.SiteProductRepository;
import com.banyuijo.foundation.service.product.site.structure.builder.SiteProductStructureBuilder;
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
public class SiteProductCreateServiceImpl implements SiteProductCreateService {

    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SiteProductStructureBuilder structureBuilder;
    private final SiteProductValidator validator;
    private final CustomLogger customLogger;

    @Override
    public Object createSiteProduct(SiteProductCreateInput request, String loginId) throws JsonProcessingException {
        validator.validateRequest(request, null);
        SiteProduct siteProduct = build(request, loginId);
        customLogger.setLogObject(siteProduct, "createSiteProduct", loginId);
        saveProduct(siteProduct);
        structureBuilder.init(siteProduct.getSiteProductId(), siteProduct.getProductTypeId(), siteProduct.getSiteProductName());
        return request;
    }
    @Transactional
    private void saveProduct(SiteProduct siteProduct){
        siteProductRepository.save(siteProduct);
    }

    private SiteProduct build (SiteProductCreateInput request, String loginId){
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
