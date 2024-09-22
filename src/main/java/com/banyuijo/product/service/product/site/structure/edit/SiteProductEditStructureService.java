package com.banyuijo.product.service.product.site.structure.edit;

import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructure;
import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductEditStructureService {
    Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId) throws JsonProcessingException;
    Object editSiteProductStructure(SiteProductEditStructure request, String loginId, UUID siteProductId) throws JsonProcessingException;
}
