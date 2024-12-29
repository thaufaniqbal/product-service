package com.banyuijo.product.service.product.site.template.list;

import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructure;

import java.util.UUID;

public interface SiteProductTemplateListService {
    SiteProductEditStructure getTemplateNotInList (UUID siteProductId);
}
