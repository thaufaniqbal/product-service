package com.banyuijo.product.service.product.site.template.detail;

import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructure;
import com.banyuijo.product.dto.product.site.template.SiteProductTemplateOutput;
import com.banyuijo.product.entity.ProductTemplateMapping;
import com.banyuijo.product.repository.ProductTemplateMappingRepository;
import com.banyuijo.product.service.product.site.structure.detail.SiteProductDetailStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductTemplateDetailServiceImpl implements SiteProductTemplateDetailService {
    private final ProductTemplateMappingRepository templateMappingRepository;
    private final SiteProductDetailStructureService structureService;

    @Override
    public SiteProductTemplateOutput getProductTemplate(UUID siteProductId) {
        SiteProductEditStructure productStructure = structureService.getEditDetail(siteProductId);
        SiteProductTemplateOutput result = new SiteProductTemplateOutput();
        result.setSiteProductId(siteProductId);
        result.setStructures(new ArrayList<>());
        for (var structure : productStructure.getStructures()){
            List<SiteProductTemplateOutput.StructureDTO> structureDTOS =
                    getProductStructureTemplate(structure);
            result.getStructures().addAll(structureDTOS);
        }

        return result;
    }
    private List<SiteProductTemplateOutput.StructureDTO> getProductStructureTemplate (SiteProductEditStructure.SiteBaseProductStructure structure){
        List<SiteProductTemplateOutput.StructureDTO> results= new ArrayList<>();

        String structureName = structure.getSiteBaseProductStructureName();
        var result = new SiteProductTemplateOutput.StructureDTO();
        result.setSiteBaseProductStructureName(structureName);
        result.setSeq(structure.getSeq());
        List<SiteProductTemplateOutput.StructureDTO.CardTemplateDTO> cartTemplates = getCartTemplates (structure);
        result.setCardTemplate(cartTemplates);
        return results;
    }
    private List<SiteProductTemplateOutput.StructureDTO.CardTemplateDTO> getCartTemplates (SiteProductEditStructure.SiteBaseProductStructure structure){
        List<SiteProductTemplateOutput.StructureDTO.CardTemplateDTO> results = new ArrayList<>();
        for (var productSetting : structure.getSettings()){
            ProductTemplateMapping productTemplateMapping = templateMappingRepository.findBySettingDataCodeIgnoreCase(productSetting.getSettingCode());
            if (Objects.nonNull(productTemplateMapping)){
                var cartTemplate = new SiteProductTemplateOutput.StructureDTO.CardTemplateDTO();
                cartTemplate.setSeq(productTemplateMapping.getSeq());
                cartTemplate.setSettingCode(productTemplateMapping.getSettingDataCode());
                cartTemplate.setComponentValue(productTemplateMapping.getComponentValue());
                results.add(cartTemplate);
            }
        }
        return results;
    }
}
