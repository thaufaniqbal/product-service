package com.iconnect.product.service.integration.company.product.site;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CompanySiteProductServiceImpl implements CompanySiteProductService {
    @Override
    public Object createProductByCompany(UUID userId, String loginId, SiteProductCreateInput input) {
        return null;
    }

    @Override
    public Object getSiteProductList(UUID userId) {
        return null;
    }

    @Override
    public Object searchSiteProduct(UUID userId, SiteProductSearchInput input) {
        return null;
    }
}
