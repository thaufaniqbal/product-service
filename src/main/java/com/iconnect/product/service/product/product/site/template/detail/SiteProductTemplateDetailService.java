package com.iconnect.product.service.product.product.site.template.detail;

import com.iconnect.product.dto.product.site.template.SiteProductTemplateOutput;

import java.util.UUID;

public interface SiteProductTemplateDetailService {
    SiteProductTemplateOutput getProductTemplate(UUID siteProductId);
}
