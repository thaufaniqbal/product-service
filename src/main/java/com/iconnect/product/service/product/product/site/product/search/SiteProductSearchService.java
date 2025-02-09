package com.iconnect.product.service.product.product.site.product.search;

import com.iconnect.product.dto.ResponsePageDTO;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;

public interface SiteProductSearchService {
    ResponsePageDTO searchSiteProduct(SiteProductSearchInput input);
}
