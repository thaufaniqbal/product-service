package com.iconnect.product.service.product.product.site.template.list;

import com.iconnect.product.dto.product.site.structure.edit.SiteProductEditStructure;

import java.util.UUID;

public interface SiteProductTemplateListService {
    SiteProductEditStructure getTemplateNotInList (UUID siteProductId);
}
