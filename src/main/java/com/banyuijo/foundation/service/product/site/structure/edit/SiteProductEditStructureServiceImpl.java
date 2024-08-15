package com.banyuijo.foundation.service.product.site.structure.edit;

import com.banyuijo.foundation.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.banyuijo.foundation.service.product.site.validator.SiteProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductEditStructureServiceImpl implements SiteProductEditStructureService {
    private final SiteProductValidator siteProductValidator;
    @Override
    public Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId) {
        siteProductValidator.validateSiteProductId(siteProductId);
        return null;
    }
}