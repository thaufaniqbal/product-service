package com.banyuijo.foundation.service.product.site;

import com.banyuijo.foundation.dto.product.site.SiteProductDto;

import java.util.UUID;

public interface SiteProductService {
    SiteProductDto getProductDetail(UUID productId);
}
