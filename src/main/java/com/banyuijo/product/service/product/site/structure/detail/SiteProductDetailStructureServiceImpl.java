package com.banyuijo.product.service.product.site.structure.detail;

import com.banyuijo.product.dto.product.site.structure.SiteProductStructure;
import com.banyuijo.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructure;
import com.banyuijo.product.entity.SiteBaseProductParent;
import com.banyuijo.product.entity.SiteBaseProductSetting;
import com.banyuijo.product.entity.SiteBaseProductSettingData;
import com.banyuijo.product.entity.SiteBaseProductStructure;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.repository.SiteBaseProductParentRepository;
import com.banyuijo.product.repository.SiteBaseProductSettingDataRepository;
import com.banyuijo.product.repository.SiteBaseProductSettingRepository;
import com.banyuijo.product.repository.SiteBaseProductStructureRepository;
import com.banyuijo.product.service.product.site.validator.SiteProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteProductDetailStructureServiceImpl implements SiteProductDetailStructureService {
    private final SiteBaseProductParentRepository parentRepository;
    private final SiteBaseProductStructureRepository structureRepository;
    private final SiteBaseProductSettingRepository settingRepository;
    private final SiteBaseProductSettingDataRepository settingDataRepository;
    private final SiteProductValidator siteProductValidator;
    @Override
    public SiteProductDetailStructureOutput getSiteProductStructureDetail(UUID siteProductId){
        siteProductValidator.validateSiteProductId(siteProductId);

        SiteProductStructure structure = build(siteProductId);
        SiteProductDetailStructureOutput output = new SiteProductDetailStructureOutput();
        output.setSiteProductId(siteProductId);
        output.setStructure(structure);
        output.setJsonStructure("{soon}");
        return output;
    }

    @Override
    public SiteProductEditStructure getEditDetail(UUID siteProductId) {
        siteProductValidator.validateSiteProductId(siteProductId);
        return buildEditDetail(siteProductId);
    }
    private SiteProductEditStructure buildEditDetail (UUID siteProductId) {
        SiteProductEditStructure output = new SiteProductEditStructure();

        SiteProductStructure productStructure = build(siteProductId);
        List<SiteProductEditStructure.SiteBaseProductStructure> detailStructureEdit = buildEditDetailStructure(productStructure.getStructures());

        output.setStructures(detailStructureEdit);
        return output;
    }
    private List<SiteProductEditStructure.SiteBaseProductStructure> buildEditDetailStructure (List<SiteProductStructure.SiteBaseProductStructure> siteBaseProductStructures){
        List<SiteProductEditStructure.SiteBaseProductStructure> output  = new ArrayList<>();
        for (SiteProductStructure.SiteBaseProductStructure productStructure : siteBaseProductStructures){
            List<SiteProductEditStructure.SiteBaseProductStructure.SiteBaseProductSetting> settings = buildEditSetting(productStructure.getSettings());
            SiteProductEditStructure.SiteBaseProductStructure result = new SiteProductEditStructure.SiteBaseProductStructure();
            result.setSeq(productStructure.getSeq());
            result.setSiteBaseProductStructureName(productStructure.getSiteBaseProductStructureName());
            result.setSettings(settings);
            output.add(result);
        }
        return output;
    }
    private List<SiteProductEditStructure.SiteBaseProductStructure.SiteBaseProductSetting> buildEditSetting (List<SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData> settingDataList){
        List<SiteProductEditStructure.SiteBaseProductStructure.SiteBaseProductSetting> output = new ArrayList<>();
        for (SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData settingData : settingDataList){
            SiteProductEditStructure.SiteBaseProductStructure.SiteBaseProductSetting result = new SiteProductEditStructure.SiteBaseProductStructure.SiteBaseProductSetting();
            result.setInput(settingData.getInput());
            result.setSeq(settingData.getSeq());
            result.setInput(settingData.getInput());
            result.setObjectName(settingData.getObjectName());
            result.setObject(settingData.getObject());
            result.setSiteBaseProductSettingTypeId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            output.add(result);
        }
        return output;
    }


    private SiteProductStructure build(UUID siteProductId) {
        SiteProductStructure output = new SiteProductStructure();

        SiteBaseProductParent productParent = parentRepository.findBySiteProductId(siteProductId);
        List<SiteProductStructure.SiteBaseProductStructure> siteBaseProductStructures = new ArrayList<>();
        if (Objects.nonNull(productParent)){
            siteBaseProductStructures = buildStructures(productParent);
            output.setSiteBaseProductParentName(productParent.getSiteBaseProductParentName());
        }
        output.setStructures(siteBaseProductStructures);
        return output;
    }

    private List<SiteProductStructure.SiteBaseProductStructure> buildStructures(SiteBaseProductParent productParent) {
        List<SiteBaseProductStructure> productStructures = structureRepository.findAllBySiteBaseProductParentId(productParent.getSiteBaseProductParentId());
        List <SiteProductStructure.SiteBaseProductStructure> productStructuresDTO = new ArrayList<>();

        if (!productStructures.isEmpty()){
            productStructuresDTO = buildStructures(productStructures);
        }

        return productStructuresDTO;
    }
    private List <SiteProductStructure.SiteBaseProductStructure> buildStructures (List<SiteBaseProductStructure> productStructures){
        List<SiteProductStructure.SiteBaseProductStructure> results = new ArrayList<>();
        for (SiteBaseProductStructure structure : productStructures){
            SiteProductStructure.SiteBaseProductStructure result = new SiteProductStructure.SiteBaseProductStructure();
            List<SiteBaseProductSetting> settings = settingRepository.findAllBySiteBaseProductStructureId(structure.getSiteBaseProductStructureId());
            List<SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData> settingsDTO = new ArrayList<>();
            if (!settings.isEmpty()){
                settingsDTO = buildSettingsData(settings);
            }
            result.setSeq(structure.getSeq());
            result.setSiteBaseProductStructureName(structure.getSiteBaseProductStructureName());
            result.setSettings(settingsDTO);
            results.add(result);
        }
        return results.stream()
                .sorted(Comparator.comparing(SiteProductStructure.SiteBaseProductStructure::getSeq))
                .collect(Collectors.toList());
    }
    private List<SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData> buildSettingsData (List<SiteBaseProductSetting> settings){
        List<SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData> results = new ArrayList<>();

        for (SiteBaseProductSetting setting : settings) {
            List<SiteBaseProductSettingData> settingsData = settingDataRepository.findAllBySiteBaseProductSettingId(setting.getSiteBaseProductSettingId());

            if (!settingsData.isEmpty()) {
                for (SiteBaseProductSettingData data : settingsData) {
                    SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData result =
                            new SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData();
                    result.setSeq(data.getSeq());
                    result.setValue(data.getValue());
                    result.setUpperBond(data.getUpperBond());
                    result.setLowerBond(data.getLowerBond());
                    result.setInput(data.getInput());
                    result.setObjectName(data.getObjectName());
                    if (Objects.isNull(result.getValue())){
                        result.setObject(BooleanStatus.NO.getCode());
                    }else {
                        result.setObject(BooleanStatus.YES.getCode());
                    }
                    results.add(result);
                }
            }
        }

        return results.stream()
                .sorted(Comparator.comparing(SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData::getSeq))
                .collect(Collectors.toList());
    }
}
