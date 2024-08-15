package com.banyuijo.foundation.service.product.site.structure.edit;

import com.banyuijo.foundation.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductEditStructureService {
    Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId) throws JsonProcessingException;
}
