package com.iconnect.product.service.product.product.site.structure.builder;

import com.iconnect.product.dto.product.site.structure.SiteProductStructureWrapper;
import com.iconnect.product.entity.product.SiteBaseProductParent;
import com.iconnect.product.entity.product.SiteBaseProductSetting;
import com.iconnect.product.entity.product.SiteBaseProductSettingData;
import com.iconnect.product.entity.product.SiteBaseProductStructure;
import com.iconnect.product.enums.InputTypeStructure;
import com.iconnect.product.enums.ObjectTypeStructure;
import com.iconnect.product.repository.product.SiteBaseProductParentRepository;
import com.iconnect.product.repository.product.SiteBaseProductSettingDataRepository;
import com.iconnect.product.repository.product.SiteBaseProductSettingRepository;
import com.iconnect.product.repository.product.SiteBaseProductStructureRepository;
import com.iconnect.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductStructureBuilderImpl implements SiteProductStructureBuilder {
    private static final String UUID_DEFAULT= "e46ff882-5865-4afb-a5e1-88d792aa7a34";
    private static final String UUID_NOT_DEFAULT= "e46ff882-5865-4afb-a5e1-88d792aa7a35";
    private static final String SYSTEM= "system";

    private final SiteBaseProductParentRepository parentRepository;
    private final SiteBaseProductStructureRepository structureRepository;
    private final SiteBaseProductSettingRepository settingRepository;
    private final SiteBaseProductSettingDataRepository settingDataRepository;

    private final CustomLogger customLogger;

    @Override
    public void init(UUID sideProductId, UUID productTypeId, String productName) throws JsonProcessingException {
        SiteBaseProductParent productParent = buildParent(sideProductId, productTypeId, productName);
        SiteBaseProductStructure productStructure = buildStructure(productParent.getSiteBaseProductParentId(), 1, "structureName1");
        SiteBaseProductStructure productStructureSecond = buildStructure(productParent.getSiteBaseProductParentId(), 2, "structureName2");
        SiteBaseProductSetting productSetting = buildStructureSetting(productStructure.getSiteBaseProductStructureId(), UUID.fromString(UUID_DEFAULT));
        SiteBaseProductSetting productSettingSecond = buildStructureSetting(productStructureSecond.getSiteBaseProductStructureId(), UUID.fromString(UUID_NOT_DEFAULT));
        SiteBaseProductSettingData productSettingData = buildStructureSettingData(productSetting.getSiteBaseProductSettingId(), 1, ObjectTypeStructure.OBJECT_TYPE1.getCode(), InputTypeStructure.INPUT_TYPE5.getCode(), "input init", "");
        SiteBaseProductSettingData productSettingDataSecond = buildStructureSettingData(productSettingSecond.getSiteBaseProductSettingId(), 1, ObjectTypeStructure.OBJECT_TYPE2.getCode(), InputTypeStructure.INPUT_TYPE7.getCode(), "input init", "");
        SiteBaseProductSettingData productSettingDataSecondT = buildStructureSettingData(productSettingSecond.getSiteBaseProductSettingId(), 2, ObjectTypeStructure.OBJECT_TYPE3.getCode(), InputTypeStructure.INPUT_TYPE3.getCode(), "display", "");

        List<SiteBaseProductStructure> structures = Arrays.asList(productStructure, productStructureSecond);
        List<SiteBaseProductSetting> settings = Arrays.asList(productSetting, productSettingSecond);
        List<SiteBaseProductSettingData> settingData = Arrays.asList(productSettingData, productSettingDataSecond, productSettingDataSecondT);

        SiteProductStructureWrapper wrapper = buildWrapper(productParent, structures, settings, settingData);
        customLogger.setLogObject(wrapper, "init structure", SYSTEM);

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
    @Override
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
    @Override
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
    @Override
    public SiteBaseProductSettingData buildStructureSettingData(UUID settingId, Integer seq, int object, Integer input, String objectName, String settingCode){
        SiteBaseProductSettingData result = new SiteBaseProductSettingData();
        ObjectTypeStructure objectTypeStructure = ObjectTypeStructure.fromCode(object);
        result.setSiteBaseProductSettingDataId(UUID.randomUUID());
        result.setSiteBaseProductSettingId(settingId);
        result.setSeq(seq);
        result.setObjectType(objectTypeStructure.getCode());
        result.setValue(objectTypeStructure.getValue());
        result.setLowerBond(objectTypeStructure.getLowerBond());
        result.setUpperBond(objectTypeStructure.getUpperBond());
        result.setInput(input);
        result.setSettingCode(settingCode.isEmpty() ? generateSettingCode() : settingCode);
        result.setObjectName(objectName);
        return result;
    }
    @Override
    public SiteProductStructureWrapper buildWrapper(SiteBaseProductParent productParent,
                                                     List<SiteBaseProductStructure> structures,
                                                     List<SiteBaseProductSetting> settings,
                                                     List<SiteBaseProductSettingData> settingData) {
        SiteProductStructureWrapper result = new SiteProductStructureWrapper();

        result.setSiteBaseProductParentId(productParent.getSiteBaseProductParentId());
        result.setSiteBaseProductParentName(productParent.getSiteBaseProductParentName());
        result.setSiteProductId(productParent.getSiteProductId());
        result.setProductTypeId(productParent.getProductTypeId());
        result.setCreatedBy(productParent.getCreatedBy());
        result.setCreatedDate(productParent.getCreatedDate());
        result.setLastUpdatedBy(productParent.getLastUpdatedBy());
        result.setLastUpdatedDate(productParent.getLastUpdatedDate());

        List<SiteProductStructureWrapper.SiteBaseProductStructure> structureWrappers = buildStructureWrappers(structures, settings, settingData);

        result.setStructures(structureWrappers);

        return result;
    }

    private List<SiteProductStructureWrapper.SiteBaseProductStructure> buildStructureWrappers(List<SiteBaseProductStructure> structures,
                                                                                              List<SiteBaseProductSetting> settings,
                                                                                              List<SiteBaseProductSettingData> settingData){
        List<SiteProductStructureWrapper.SiteBaseProductStructure> structureWrappers = new ArrayList<>();
        for (SiteBaseProductStructure structure : structures) {
            SiteProductStructureWrapper.SiteBaseProductStructure structureWrapper = new SiteProductStructureWrapper.SiteBaseProductStructure();
            structureWrapper.setSiteBaseProductStructureId(structure.getSiteBaseProductStructureId());
            structureWrapper.setSiteBaseProductStructureName(structure.getSiteBaseProductStructureName());
            structureWrapper.setSiteBaseProductParentId(structure.getSiteBaseProductParentId());
            structureWrapper.setSeq(structure.getSeq());
            structureWrapper.setCreatedBy(structure.getCreatedBy());
            structureWrapper.setCreatedDate(structure.getCreatedDate());
            structureWrapper.setLastUpdatedBy(structure.getLastUpdatedBy());
            structureWrapper.setLastUpdatedDate(structure.getLastUpdatedDate());

            List<SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting> settingWrappers = buildSettingWrappers(structure, settings, settingData);

            structureWrapper.setSettings(settingWrappers);
            structureWrappers.add(structureWrapper);
        }
        return structureWrappers;
    }

    private List<SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting> buildSettingWrappers (SiteBaseProductStructure structure,
                                                                                                                    List<SiteBaseProductSetting> settings,
                                                                                                                    List<SiteBaseProductSettingData> settingData){
        List<SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting> settingWrappers = new ArrayList<>();
        for (SiteBaseProductSetting setting : settings) {
            if (setting.getSiteBaseProductStructureId().equals(structure.getSiteBaseProductStructureId())) {
                SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting settingWrapper = new SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting();
                settingWrapper.setSiteBaseProductSettingId(setting.getSiteBaseProductSettingId());
                settingWrapper.setSiteBaseProductSettingTypeId(setting.getSiteBaseProductSettingTypeId());
                settingWrapper.setSiteBaseProductStructureId(setting.getSiteBaseProductStructureId());
                settingWrapper.setCreatedBy(setting.getCreatedBy());
                settingWrapper.setCreatedDate(setting.getCreatedDate());
                settingWrapper.setLastUpdatedBy(setting.getLastUpdatedBy());
                settingWrapper.setLastUpdatedDate(setting.getLastUpdatedDate());

                List<SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData> settingDataWrappers = buildSettingDataWrappers(setting, settingData);

                settingWrapper.setSettingData(settingDataWrappers);
                settingWrappers.add(settingWrapper);
            }
        }
        return settingWrappers;
    }
    private List<SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData> buildSettingDataWrappers (SiteBaseProductSetting setting,
                                                                                                                                                   List<SiteBaseProductSettingData> settingData){
        List<SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData> settingDataWrappers = new ArrayList<>();
        for (SiteBaseProductSettingData data : settingData) {
            if (data.getSiteBaseProductSettingId().equals(setting.getSiteBaseProductSettingId())) {
                SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData dataWrapper = new SiteProductStructureWrapper.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData();
                dataWrapper.setSiteBaseProductSettingDataId(data.getSiteBaseProductSettingDataId());
                dataWrapper.setSiteBaseProductSettingId(data.getSiteBaseProductSettingId());
                dataWrapper.setSeq(data.getSeq());
                dataWrapper.setValue(data.getValue());
                dataWrapper.setUpperBond(data.getUpperBond());
                dataWrapper.setLowerBond(data.getLowerBond());

                settingDataWrappers.add(dataWrapper);
            }
        }
        return settingDataWrappers;
    }

    public static String generateSettingCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 5);
    }
}
