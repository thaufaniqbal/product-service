package com.iconnect.product.service.product.product.site.product.create;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.entity.product.ProductType;
import com.iconnect.product.entity.product.SiteProduct;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.repository.product.ProductTypeRepository;
import com.iconnect.product.repository.product.SiteProductRepository;
import com.iconnect.product.service.product.product.site.structure.builder.SiteProductStructureBuilder;
import com.iconnect.product.service.product.product.site.validator.SiteProductValidator;
import com.iconnect.product.util.CustomLogger;
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
        return siteProduct;
    }
    @Transactional
    private void saveProduct(SiteProduct siteProduct){
        siteProductRepository.save(siteProduct);
    }

    private SiteProduct build (SiteProductCreateInput request, String loginId){
        ProductType productType = productTypeRepository.findByProductTypeId(request.getProductTypeId());

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
