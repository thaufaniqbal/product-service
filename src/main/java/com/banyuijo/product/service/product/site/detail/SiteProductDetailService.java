package com.banyuijo.product.service.product.site.detail;

import com.banyuijo.product.dto.product.site.SiteProductDetailOutput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductDetailService {
    SiteProductDetailOutput getSiteProductDetail(UUID productId) throws JsonProcessingException;
}
