package com.iconnect.product.service.product.site.product.search;

import com.iconnect.product.dto.base.ResponsePageDTO;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;

public interface SiteProductSearchService {
    ResponsePageDTO searchSiteProduct(SiteProductSearchInput input);
}
