package com.iconnect.product.service.product.product.site.structure.edit;

import com.iconnect.product.dto.product.site.structure.edit.SiteProductEditStructure;
import com.iconnect.product.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductEditStructureService {
    //for editable many setting type to setting data
    Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId) throws JsonProcessingException;
    Object editSiteProductStructure(SiteProductEditStructure request, String loginId, UUID siteProductId) throws JsonProcessingException;
}
