package com.banyuijo.foundation.service.product.site.structure.builder;

import com.banyuijo.foundation.entity.SiteBaseProductParent;
import com.banyuijo.foundation.entity.SiteBaseProductSetting;
import com.banyuijo.foundation.entity.SiteBaseProductSettingData;
import com.banyuijo.foundation.entity.SiteBaseProductStructure;
import com.banyuijo.foundation.repository.SiteBaseProductParentRepository;
import com.banyuijo.foundation.repository.SiteBaseProductSettingDataRepository;
import com.banyuijo.foundation.repository.SiteBaseProductSettingRepository;
import com.banyuijo.foundation.repository.SiteBaseProductStructureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductStructureBuilderImpl implements SiteProductStructureBuilder {
    private static final String UUID_DEFAULT= "00000000000000000000000000000000";
    private static final String UUID_NOT_DEFAULT= "00000000000000000000000000000001";
    private static final String SYSTEM= "system";

    private final SiteBaseProductParentRepository parentRepository;
    private final SiteBaseProductStructureRepository structureRepository;
    private final SiteBaseProductSettingRepository settingRepository;
    private final SiteBaseProductSettingDataRepository settingDataRepository;

    @Override
    public void init(UUID sideProductId, UUID productTypeId, String productName) {
        SiteBaseProductParent productParent = buildParent(sideProductId, productTypeId, productName);
        SiteBaseProductStructure productStructure = buildStructure(productParent.getSiteBaseProductParentId(), 1, "structureName1");
        SiteBaseProductStructure productStructureSecond = buildStructure(productParent.getSiteBaseProductParentId(), 2, "structureName2");
        SiteBaseProductSetting productSetting = buildStructureSetting(productStructure.getSiteBaseProductStructureId(), UUID.fromString(UUID_DEFAULT));
        SiteBaseProductSetting productSettingSecond = buildStructureSetting(productStructureSecond.getSiteBaseProductStructureId(), UUID.fromString(UUID_NOT_DEFAULT));
        SiteBaseProductSettingData productSettingData = buildStructureSettingData(productSetting.getSiteBaseProductSettingId(), 1, true);
        SiteBaseProductSettingData productSettingDataSecond = buildStructureSettingData(productSettingSecond.getSiteBaseProductSettingId(), 1, false);
        SiteBaseProductSettingData productSettingDataSecondT = buildStructureSettingData(productSettingSecond.getSiteBaseProductSettingId(), 2, false);

        parentRepository.save(productParent);
        structureRepository.save(productStructure);
        structureRepository.save(productStructureSecond);
        settingRepository.save(productSetting);
        settingRepository.save(productSettingSecond);
        settingDataRepository.save(productSettingData);
        settingDataRepository.save(productSettingDataSecond);
        settingDataRepository.save(productSettingDataSecondT);
    }

    @Override
    public SiteBaseProductParent buildParent(UUID sideProductId, UUID productTypeId, String productName){
        SiteBaseProductParent result = new SiteBaseProductParent();
        result.setSiteBaseProductParentId(UUID.randomUUID());
        result.setSiteBaseProductParentName(productName);
        result.setSiteProductId(sideProductId);
        result.setProductTypeId(productTypeId);
        result.setCreatedBy(SYSTEM);
        result.setCreatedDate(LocalDateTime.now());
        result.setLastUpdatedBy(SYSTEM);
        result.setLastUpdatedDate(LocalDateTime.now());
        return result;
    }

    public SiteBaseProductStructure buildStructure(UUID parentId, Integer seq, String structureName){
        SiteBaseProductStructure result = new SiteBaseProductStructure();
        result.setSiteBaseProductStructureId(UUID.randomUUID());
        result.setSiteBaseProductStructureName(structureName);
        result.setSiteBaseProductParentId(parentId);
        result.setSeq(seq);
        result.setCreatedBy(SYSTEM);
        result.setCreatedDate(LocalDateTime.now());
        result.setLastUpdatedBy(SYSTEM);
        result.setLastUpdatedDate(LocalDateTime.now());
        return result;
    }

    public SiteBaseProductSetting buildStructureSetting(UUID structureId, UUID settingTypeId){
        SiteBaseProductSetting result = new SiteBaseProductSetting();
        result.setSiteBaseProductSettingId(UUID.randomUUID());
        result.setSiteBaseProductSettingTypeId(settingTypeId);
        result.setSiteBaseProductStructureId(structureId);
        result.setCreatedBy(SYSTEM);
        result.setCreatedDate(LocalDateTime.now());
        result.setLastUpdatedBy(SYSTEM);
        result.setLastUpdatedDate(LocalDateTime.now());
        return result;
    }

    public SiteBaseProductSettingData buildStructureSettingData(UUID settingId, Integer seq, boolean object){
        SiteBaseProductSettingData result = new SiteBaseProductSettingData();
        result.setSiteBaseProductSettingDataId(UUID.randomUUID());
        result.setSiteBaseProductSettingId(settingId);
        result.setSeq(seq);
        if (object){
            result.setValue("{format}");
        }else {
            result.setLowerBond("{lower}");
            result.setUpperBond("{upper}");
        }
        return result;
    }

}
