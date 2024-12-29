package com.banyuijo.product.service.product.site.product.edit;

import com.banyuijo.product.dto.product.site.product.SiteProductEditInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductEditService {
    Object editSiteProduct(SiteProductEditInput request, String loginId, UUID siteProductId) throws JsonProcessingException;

}
