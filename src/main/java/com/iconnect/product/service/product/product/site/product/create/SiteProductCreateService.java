package com.iconnect.product.service.product.product.site.product.create;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SiteProductCreateService {
    Object createSiteProduct(SiteProductCreateInput request, String loginId) throws JsonProcessingException;
}
