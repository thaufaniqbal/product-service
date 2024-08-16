package com.banyuijo.product.service.product.site.detail;

import com.banyuijo.product.dto.product.site.SiteProductDetailOutput;

import java.util.UUID;

public interface SiteProductDetailService {
    SiteProductDetailOutput getSiteProductDetail(UUID productId);
}
