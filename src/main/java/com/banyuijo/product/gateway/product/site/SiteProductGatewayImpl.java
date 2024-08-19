package com.banyuijo.product.gateway.product.site;

import com.banyuijo.product.dto.product.site.SiteProductListOutput;
import com.banyuijo.product.dto.product.site.SiteProductSearchOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class SiteProductGatewayImpl implements SiteProductGateway {
    @Override
    public List<SiteProductListOutput> getListByDeleteStatusAndProductTypeId(int deleteStatus, UUID productTypeId) {
        return null;
    }

    @Override
    public Page<SiteProductSearchOutput> getSearchSiteProduct(String productCode, String productName, UUID productTypeId, Pageable pageable) {
        return null;
    }
}
