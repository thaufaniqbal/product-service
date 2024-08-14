package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.site.SiteProductInput;

public interface SiteProductCreateService {
    Object createProduct(SiteProductInput request, String loginId);
}
