package com.banyuijo.product.service.product.site.delete;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductDeleteService {
    Object deleteSiteProduct(UUID siteProductId, String loginId) throws JsonProcessingException;

}
