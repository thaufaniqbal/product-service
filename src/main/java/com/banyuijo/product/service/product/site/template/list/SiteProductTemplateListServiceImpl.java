package com.banyuijo.product.service.product.site.template.list;

import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructure;
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
