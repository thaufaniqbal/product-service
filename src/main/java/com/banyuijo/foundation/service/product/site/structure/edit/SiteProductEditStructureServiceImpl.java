package com.banyuijo.foundation.service.product.site.structure.edit;

import com.banyuijo.foundation.dto.product.site.structure.SiteProductStructureEditWrapper;
import com.banyuijo.foundation.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.banyuijo.foundation.entity.SiteBaseProductParent;
import com.banyuijo.foundation.entity.SiteBaseProductSetting;
import com.banyuijo.foundation.entity.SiteBaseProductSettingData;
import com.banyuijo.foundation.entity.SiteBaseProductStructure;
import com.banyuijo.foundation.enums.BooleanStatus;
import com.banyuijo.foundation.repository.SiteBaseProductParentRepository;
import com.banyuijo.foundation.repository.SiteBaseProductSettingDataRepository;
import com.banyuijo.foundation.repository.SiteBaseProductSettingRepository;
import com.banyuijo.foundation.repository.SiteBaseProductStructureRepository;
import com.banyuijo.foundation.service.product.site.structure.builder.SiteProductStructureBuilder;
import com.banyuijo.foundation.service.product.site.validator.SiteProductValidator;
import com.banyuijo.foundation.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Object editSiteProductStructure(SiteProductEditStructureInput request, String loginId, UUID siteProductId) throws JsonProcessingException {
        siteProductValidator.validateSiteProductId(siteProductId);
        SiteBaseProductParent productParent = parentRepository.findBySiteProductId(siteProductId);

        SiteProductStructureEditWrapper wrapper = new SiteProductStructureEditWrapper();

        buildStructure(productParent, request, wrapper);

        customLogger.setLogObject(wrapper, "editSiteProductStructure", loginId);

        structureRepository.saveAll(wrapper.getStructures());
        settingRepository.saveAll(wrapper.getSettings());
        settingDataRepository.saveAll(wrapper.getSettingData());
        return request;
    }
    private void  buildStructure(SiteBaseProductParent productParent, SiteProductEditStructureInput request, SiteProductStructureEditWrapper wrapper){
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
