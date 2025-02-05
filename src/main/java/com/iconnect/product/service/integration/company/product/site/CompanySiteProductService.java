package com.iconnect.product.service.integration.company.product.site;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;

import java.util.UUID;

public interface CompanySiteProductService {
    Object createProductByCompany (UUID userId, SiteProductCreateInput input);

}
