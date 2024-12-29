package com.iconnect.product.gateway.product.site;

import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SiteProductGateway {
    List<SiteProductListOutput> getListByDeleteStatusAndProductTypeId(int deleteStatus, UUID productTypeId);

    Page<SiteProductSearchOutput> getSearchSiteProduct (String productCode, String productName, UUID productTypeId, Pageable pageable);

}
