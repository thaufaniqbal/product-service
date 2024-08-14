package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.site.SiteProductCreateInput;

public interface SiteProductCreateService {
    Object createProduct(SiteProductCreateInput request, String loginId);
}
