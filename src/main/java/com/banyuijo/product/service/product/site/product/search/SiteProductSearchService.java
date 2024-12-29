package com.banyuijo.product.service.product.site.product.search;

import com.banyuijo.product.dto.base.ResponsePageDTO;
import com.banyuijo.product.dto.product.site.product.SiteProductSearchInput;

public interface SiteProductSearchService {
    ResponsePageDTO searchSiteProduct(SiteProductSearchInput input);
}
