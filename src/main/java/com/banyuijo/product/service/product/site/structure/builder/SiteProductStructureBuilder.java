package com.banyuijo.product.service.product.site.structure.builder;

import com.banyuijo.product.dto.product.site.structure.SiteProductStructureWrapper;
import com.banyuijo.product.entity.SiteBaseProductParent;
import com.banyuijo.product.entity.SiteBaseProductSetting;
import com.banyuijo.product.entity.SiteBaseProductSettingData;
import com.banyuijo.product.entity.SiteBaseProductStructure;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.UUID;

public interface SiteProductStructureBuilder {
    void init(UUID sideProductId, UUID productTypeId, String productName) throws JsonProcessingException;
    SiteBaseProductParent buildParent(UUID sideProductId, UUID productTypeId, String productName);
    SiteBaseProductStructure buildStructure(UUID parentId, Integer seq, String structureName);
    SiteBaseProductSetting buildStructureSetting(UUID structureId, UUID settingTypeId);
    SiteBaseProductSettingData buildStructureSettingData(UUID settingId, Integer seq, int object, Integer input, String objectName, String settingCode);
    SiteProductStructureWrapper buildWrapper(SiteBaseProductParent productParent,
                                             List<SiteBaseProductStructure> structures,
                                             List<SiteBaseProductSetting> settings,
                                             List<SiteBaseProductSettingData> settingData);
}
