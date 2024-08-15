package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.site.SiteProductCreateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SiteProductCreateService {
    Object createSiteProduct(SiteProductCreateInput request, String loginId) throws JsonProcessingException;
}
