package com.banyuijo.product.service.product.site.product.detail;

import com.banyuijo.product.dto.product.site.product.SiteProductDetailOutput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductDetailService {
    SiteProductDetailOutput getSiteProductDetail(UUID productId) throws JsonProcessingException;
}
