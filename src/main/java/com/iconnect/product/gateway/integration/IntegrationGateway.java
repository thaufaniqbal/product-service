package com.iconnect.product.gateway.integration;

import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchOutput;
import com.iconnect.product.dto.product.type.ProductTypeListOutput;
import com.iconnect.product.dto.product.type.ProductTypeSearchOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IntegrationGateway {
    List<SiteProductListOutput> getListSiteProduct (UUID companyId, Integer deleteStatus, UUID productTypeId);
    Page<SiteProductSearchOutput> getSearchSiteProduct (UUID companyId, String productCode, String productName, UUID productTypeId, Pageable pageable);
    List<ProductTypeListOutput> getListProductType (UUID companyId, Integer deleteStatus);
    Page<ProductTypeSearchOutput> getSearchProductType (UUID companyId, String productTypeCode, String productName, Pageable pageable);

}
