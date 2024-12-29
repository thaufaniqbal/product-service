package com.banyuijo.product.service.product.site.template.create;

import com.banyuijo.product.dto.product.site.template.SiteProductTemplateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductTemplateCreateService {
    Object createProductTemplate(SiteProductTemplateInput request, UUID siteProductId) throws JsonProcessingException;
}
