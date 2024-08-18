package com.banyuijo.product.service.product.site.search;

import com.banyuijo.product.dto.base.ResponsePageDTO;
import com.banyuijo.product.dto.product.site.SiteProductSearchInput;

public interface SiteProductSearchService {
    ResponsePageDTO searchSiteProduct(SiteProductSearchInput input);
}
