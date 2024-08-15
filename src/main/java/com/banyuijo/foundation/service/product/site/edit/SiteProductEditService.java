package com.banyuijo.foundation.service.product.site.edit;

import com.banyuijo.foundation.dto.product.site.SiteProductEditInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductEditService {
    Object editSiteProduct(SiteProductEditInput request, String loginId, UUID siteProductId) throws JsonProcessingException;

}
