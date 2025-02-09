package com.iconnect.product.service.integration.company.product.site;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;

import java.util.UUID;

public interface CompanySiteProductService {
    Object createProductByCompany (UUID userId, String loginId,SiteProductCreateInput input);
    Object getSiteProductList (UUID userId);
    Object searchSiteProduct (UUID userId, SiteProductSearchInput input);

}
