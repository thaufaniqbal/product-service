package com.banyuijo.product.service.product.site.create;

import com.banyuijo.product.dto.product.site.SiteProductCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SiteProductCreateService {
    Object createSiteProduct(SiteProductCreateInput request, String loginId) throws JsonProcessingException;
}
