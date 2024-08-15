package com.banyuijo.foundation.service.product.site.structure.detail;

import com.banyuijo.foundation.dto.product.site.structure.detail.SiteProductDetailStructureOutput;

import java.util.UUID;

public interface SiteProductDetailStructureService {
    SiteProductDetailStructureOutput getSiteProductStructureDetail(UUID siteProductId);

}
