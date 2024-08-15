package com.banyuijo.foundation.service.product.site.structure.builder;

import com.banyuijo.foundation.entity.SiteBaseProductParent;
import com.banyuijo.foundation.entity.SiteBaseProductSetting;
import com.banyuijo.foundation.entity.SiteBaseProductSettingData;
import com.banyuijo.foundation.entity.SiteBaseProductStructure;

import java.util.UUID;

public interface SiteProductStructureBuilder {
    void init(UUID sideProductId, UUID productTypeId, String productName);
    SiteBaseProductParent buildParent(UUID sideProductId, UUID productTypeId, String productName);
    SiteBaseProductStructure buildStructure(UUID parentId, Integer seq, String structureName);
    SiteBaseProductSetting buildStructureSetting(UUID structureId, UUID settingTypeId);

    SiteBaseProductSettingData buildStructureSettingData(UUID settingId, Integer seq, boolean object);
}
