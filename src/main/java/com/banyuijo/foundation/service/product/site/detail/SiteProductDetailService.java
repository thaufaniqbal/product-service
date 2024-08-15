package com.banyuijo.foundation.service.product.site.detail;

import com.banyuijo.foundation.dto.product.site.SiteProductDto;

import java.util.UUID;

public interface SiteProductDetailService {
    SiteProductDto getProductDetail(UUID productId);
}
