package com.banyuijo.foundation.service.product.site.detail;

import com.banyuijo.foundation.dto.product.site.SiteProductDetailOutput;

import java.util.UUID;

public interface SiteProductDetailService {
    SiteProductDetailOutput getSiteProductDetail(UUID productId);
}
