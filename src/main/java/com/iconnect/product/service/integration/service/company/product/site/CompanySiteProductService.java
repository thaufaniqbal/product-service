package com.iconnect.product.service.integration.service.company.product.site;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;

import java.util.UUID;

public interface CompanySiteProductService {
    Object createProductByCompany (UUID userId, String loginId,SiteProductCreateInput input) throws JsonProcessingException;
    Object getSiteProductList (UUID userId);
    Object searchSiteProduct (UUID userId, SiteProductSearchInput input);

}
