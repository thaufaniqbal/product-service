package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.SiteProductCreateRequest;

public interface SiteProductCreateService {
    Object createProduct(SiteProductCreateRequest request, String loginId);
}
