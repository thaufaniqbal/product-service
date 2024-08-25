package com.banyuijo.product.service.product.site.structure.edit;

import com.banyuijo.product.dto.product.site.structure.SiteProductStructureEditWrapper;
import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.banyuijo.product.entity.SiteBaseProductParent;
import com.banyuijo.product.entity.SiteBaseProductSetting;
import com.banyuijo.product.entity.SiteBaseProductSettingData;
import com.banyuijo.product.entity.SiteBaseProductStructure;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.repository.SiteBaseProductParentRepository;
import com.banyuijo.product.repository.SiteBaseProductSettingDataRepository;
import com.banyuijo.product.repository.SiteBaseProductSettingRepository;
import com.banyuijo.product.repository.SiteBaseProductStructureRepository;
import com.banyuijo.product.service.product.site.structure.builder.SiteProductStructureBuilder;
import com.banyuijo.product.service.product.site.validator.SiteProductValidator;
import com.banyuijo.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductEditStructureServiceImpl implements SiteProductEditStructureService {
    private final SiteBaseProductParentRepository parentRepository;
    private final SiteBaseProductStructureRepository structureRepository;
    private final SiteBaseProductSettingRepository settingRepository;
    private final SiteBaseProductSettingDataRepository settingDataRepository;

    private final SiteProductStructureBuilder builder;
    private final SiteProductValidator siteProductValidator;
    private final CustomLogger customLogger;
    @Override
    @Transactional
    public Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId) throws JsonProcessingException {
        siteProductValidator.validateSiteProductId(siteProductId);
        SiteBaseProductParent productParent = parentRepository.findBySiteProductId(siteProductId);

        deleteStructure(productParent);

        SiteProductStructureEditWrapper wrapper = new SiteProductStructureEditWrapper();

        buildStructure(productParent, request, wrapper);

        customLogger.setLogObject(wrapper, "editSiteProductStructure", loginId);

        save(wrapper);

        return request;
    }
    @Transactional
    private void deleteStructure (SiteBaseProductParent productParent){
        List<SiteBaseProductStructure> structures = structureRepository.findAllBySiteBaseProductParentId(productParent.getSiteBaseProductParentId());
        for (SiteBaseProductStructure structure: structures){
            List<SiteBaseProductSetting> settings = settingRepository.findAllBySiteBaseProductStructureId(structure.getSiteBaseProductStructureId());
            for (SiteBaseProductSetting setting : settings){
                List<SiteBaseProductSettingData> settingDataList = settingDataRepository.findAllBySiteBaseProductSettingId(setting.getSiteBaseProductSettingId());
                settingDataRepository.deleteAll(settingDataList);
            }
            settingRepository.deleteAll(settings);
        }
        structureRepository.deleteAll(structures);
    }
    @Transactional
    private void save (SiteProductStructureEditWrapper wrapper){
        structureRepository.saveAll(wrapper.getStructures());
        settingRepository.saveAll(wrapper.getSettings());
        settingDataRepository.saveAll(wrapper.getSettingData());
    }
    private void  buildStructure(SiteBaseProductParent productParent, SiteProductEditStructureInput request, SiteProductStructureEditWrapper wrapper){
        wrapper.setStructures(new ArrayList<>());
        wrapper.setSettings(new ArrayList<>());
        wrapper.setSettingData(new ArrayList<>());
        for (SiteProductEditStructureInput.SiteBaseProductStructure structureRequest: request.getStructures()){
            SiteBaseProductStructure structure =
                    builder.buildStructure(
                            productParent.getSiteBaseProductParentId(),
                            structureRequest.getSeq(),
                            structureRequest.getSiteBaseProductStructureName()
                    );
            buildSetting(structure, structureRequest.getSettings(), wrapper);
            wrapper.getStructures().add(structure);
        }
    }

    private void buildSetting(SiteBaseProductStructure structure,
                              List<SiteProductEditStructureInput.SiteBaseProductStructure.SiteBaseProductSetting> settingsRequestList,
                                                      SiteProductStructureEditWrapper wrapper){
        for (SiteProductEditStructureInput.SiteBaseProductStructure.SiteBaseProductSetting settingRequest : settingsRequestList){
            SiteBaseProductSetting setting;
            setting = builder.buildStructureSetting(structure.getSiteBaseProductStructureId(), settingRequest.getSiteBaseProductSettingTypeId());
            buildSettingData(setting, settingRequest.getSettingData(), wrapper);
            wrapper.getSettings().add(setting);
        }
    }
    private void buildSettingData (SiteBaseProductSetting setting,
                                   List<SiteProductEditStructureInput.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData> settingDataRequestList,
                                   SiteProductStructureEditWrapper wrapper){
        for (SiteProductEditStructureInput.SiteBaseProductStructure.SiteBaseProductSetting.SiteBaseProductSettingData settingDataRequest : settingDataRequestList){
            SiteBaseProductSettingData settingData;
            settingData = builder.buildStructureSettingData(setting.getSiteBaseProductSettingId(),
                    settingDataRequest.getSeq(), BooleanStatus.fromCode(settingDataRequest.getObject()).getBooleanStatus());
            wrapper.getSettingData().add(settingData);
        }
    }
}
