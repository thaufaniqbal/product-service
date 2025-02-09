package com.iconnect.product.service.product.product.site.structure.detail;

import com.iconnect.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.iconnect.product.dto.product.site.structure.edit.SiteProductEditStructure;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductDetailStructureService {
    SiteProductDetailStructureOutput getSiteProductStructureDetail(UUID siteProductId) throws JsonProcessingException;
    SiteProductEditStructure getEditDetail (UUID siteProductId);

}
