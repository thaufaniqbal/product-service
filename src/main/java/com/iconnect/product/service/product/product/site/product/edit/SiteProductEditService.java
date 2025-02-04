package com.iconnect.product.service.product.product.site.product.edit;

import com.iconnect.product.dto.product.site.product.SiteProductEditInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductEditService {
    Object editSiteProduct(SiteProductEditInput request, String loginId, UUID siteProductId) throws JsonProcessingException;

}
