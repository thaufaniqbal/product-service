package com.banyuijo.foundation.service.product.site.structure.edit;

import com.banyuijo.foundation.dto.product.site.structure.edit.SiteProductEditStructureInput;

import java.util.UUID;

public interface SiteProductEditStructureService {
    Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId);
}
