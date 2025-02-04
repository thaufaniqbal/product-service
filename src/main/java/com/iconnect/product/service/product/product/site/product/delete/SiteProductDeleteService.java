package com.iconnect.product.service.product.product.site.product.delete;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductDeleteService {
    Object deleteSiteProduct(UUID siteProductId, String loginId) throws JsonProcessingException;

}
