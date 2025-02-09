package com.iconnect.product.service.product.product.site.template.create;

import com.iconnect.product.dto.product.site.template.SiteProductTemplateInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.UUID;

public interface SiteProductTemplateCreateService {
    Object createProductTemplate(SiteProductTemplateInput request, UUID siteProductId) throws JsonProcessingException;
}
