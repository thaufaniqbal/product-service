package com.banyuijo.product.service.product.site.product.create;

import com.banyuijo.product.dto.product.site.product.SiteProductCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SiteProductCreateService {
    Object createSiteProduct(SiteProductCreateInput request, String loginId) throws JsonProcessingException;
}
