package com.banyuijo.foundation.service.product.site.list;

import com.banyuijo.foundation.dto.product.site.SiteProductListOutput;

import java.util.List;
import java.util.UUID;

public interface SiteProductListService {
    List<SiteProductListOutput> getSiteProductList();
    List<SiteProductListOutput> getSiteProductListByProductType(UUID productTypeId);
}
