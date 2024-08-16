package com.banyuijo.product.service.product.site.list;

import com.banyuijo.product.dto.product.site.SiteProductListOutput;

import java.util.List;
import java.util.UUID;

public interface SiteProductListService {
    List<SiteProductListOutput> getSiteProductList();
    List<SiteProductListOutput> getSiteProductListByProductType(UUID productTypeId);
}
