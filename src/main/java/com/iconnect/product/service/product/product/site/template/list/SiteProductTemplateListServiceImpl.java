package com.iconnect.product.service.product.product.site.template.list;

import com.iconnect.product.dto.product.site.structure.edit.SiteProductEditStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductTemplateListServiceImpl implements SiteProductTemplateListService {
    @Override
    public SiteProductEditStructure getTemplateNotInList(UUID siteProductId) {
        return null;
    }
}
