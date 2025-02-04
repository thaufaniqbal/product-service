package com.iconnect.product.service.product.product.site.structure.builder;

import com.iconnect.product.dto.product.site.structure.SiteProductStructureWrapper;
import com.iconnect.product.entity.SiteBaseProductParent;
import com.iconnect.product.entity.SiteBaseProductSetting;
import com.iconnect.product.entity.SiteBaseProductSettingData;
import com.iconnect.product.entity.SiteBaseProductStructure;
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
