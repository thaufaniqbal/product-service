package com.banyuijo.product.service.product.site.template.detail;

import com.banyuijo.product.dto.product.site.template.SiteProductTemplateInput;
import com.banyuijo.product.dto.product.site.template.SiteProductTemplateOutput;

import java.util.UUID;

public interface SiteProductTemplateDetailService {
    SiteProductTemplateOutput getProductTemplate(UUID siteProductId);
}
