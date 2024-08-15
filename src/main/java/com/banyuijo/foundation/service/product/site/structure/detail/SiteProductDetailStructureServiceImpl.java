package com.banyuijo.foundation.service.product.site.structure.detail;

import com.banyuijo.foundation.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.banyuijo.foundation.repository.SiteProductRepository;
import com.banyuijo.foundation.service.product.site.validator.SiteProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductDetailStructureServiceImpl implements SiteProductDetailStructureService {
    private final SiteProductValidator siteProductValidator;
    @Override
    public SiteProductDetailStructureOutput getSiteProductStructureDetail(UUID siteProductId) {
        siteProductValidator.validateSiteProductId(siteProductId);
        return null;
    }
}
