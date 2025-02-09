package com.iconnect.product.service.product.product.site.product.detail;

import com.iconnect.product.dto.product.site.product.SiteProductDetailOutput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductDetailService {
    SiteProductDetailOutput getSiteProductDetail(UUID productId) throws JsonProcessingException;
}
