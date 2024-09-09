package com.banyuijo.product.service.product.site.delete;

import com.banyuijo.product.entity.SiteProduct;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.repository.SiteProductRepository;
import com.banyuijo.product.service.product.site.validator.SiteProductValidator;
import com.banyuijo.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductDeleteServiceImpl implements SiteProductDeleteService {
    private final SiteProductRepository siteProductRepository;
    private final SiteProductValidator validator;
    private final CustomLogger customLogger;
    @Override
    public Object deleteSiteProduct(UUID siteProductId, String loginId) throws JsonProcessingException {
        validator.validateSiteProductId(siteProductId);
        SiteProduct siteProduct = siteProductRepository.findBySiteProductId(siteProductId);
        siteProduct.setDeleteStatus(BooleanStatus.YES.getCode());
        customLogger.setLogObject(siteProduct, "deleteSiteProduct", loginId);
        siteProductRepository.save(siteProduct);
        return siteProduct;
    }
}
