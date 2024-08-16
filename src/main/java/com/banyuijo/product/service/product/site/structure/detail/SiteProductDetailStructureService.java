package com.banyuijo.product.service.product.site.structure.detail;

import com.banyuijo.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;

import java.util.UUID;

public interface SiteProductDetailStructureService {
    SiteProductDetailStructureOutput getSiteProductStructureDetail(UUID siteProductId);

}
