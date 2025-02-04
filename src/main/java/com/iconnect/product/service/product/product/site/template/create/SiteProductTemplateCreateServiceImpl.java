package com.iconnect.product.service.product.product.site.template.create;

import com.iconnect.product.dto.product.site.template.SiteProductTemplateInput;
import com.iconnect.product.entity.ProductTemplate;
import com.iconnect.product.entity.ProductTemplateMapping;
import com.iconnect.product.repository.ProductTemplateMappingRepository;
import com.iconnect.product.repository.ProductTemplateRepository;
import com.iconnect.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteProductTemplateCreateServiceImpl implements SiteProductTemplateCreateService {
    private final CustomLogger customLogger;
    private final ProductTemplateRepository templateRepository;
    private final ProductTemplateMappingRepository templateMappingRepository;
    @Override
    @Transactional
    public Object createProductTemplate(SiteProductTemplateInput request, UUID siteProductId) throws JsonProcessingException {
        ProductTemplate productTemplate = getOrNew(siteProductId);
        customLogger.setLogObject(productTemplate);
        List<ProductTemplateMapping> productTemplateMappingsFromRepo = templateMappingRepository.findAllByProductTemplateId(productTemplate.getProductTemplateId());

        List<ProductTemplateMapping> productTemplateMappingsSaved = new ArrayList<>();

        for (var structure : request.getStructures()){
            for (var cardTemplate : structure.getCardTemplate()){
                ProductTemplateMapping productTemplateMapping = getOrNew(productTemplateMappingsFromRepo, cardTemplate.getSettingCode(), productTemplate);
                productTemplateMapping.setSeq(cardTemplate.getSeq());
                productTemplateMapping.setComponentValue(cardTemplate.getComponentValue());
                productTemplateMappingsSaved.add(productTemplateMapping);
            }
        }
        templateRepository.save(productTemplate);
        List<ProductTemplateMapping> mappingsToDelete = productTemplateMappingsFromRepo.stream()
                .filter(existing -> productTemplateMappingsSaved.stream()
                        .noneMatch(newMapping ->
                                Objects.equals(existing.getSettingDataCode(), newMapping.getSettingDataCode())))
                .collect(Collectors.toList());
        templateMappingRepository.deleteAll(mappingsToDelete);
        templateMappingRepository.saveAll(productTemplateMappingsSaved);

        return productTemplateMappingsSaved;
    }

    private ProductTemplate getOrNew (UUID siteProductId){
        return templateRepository.findBySiteProductId(siteProductId)
                .orElse(new ProductTemplate(UUID.randomUUID(), siteProductId));
    }

    private ProductTemplateMapping getOrNew (List<ProductTemplateMapping>productTemplateMappings, String settingDataCode, ProductTemplate productTemplate){
        ProductTemplateMapping result = null;
        for (ProductTemplateMapping productTemplateMapping : productTemplateMappings){
            if (productTemplateMapping.getSettingDataCode().equals(settingDataCode)
                    && productTemplateMapping.getProductTemplateId().equals(productTemplate.getProductTemplateId())){
                result = productTemplateMapping;
            }
        }
        if (Objects.isNull(result)){
            result = new ProductTemplateMapping();
            result.setProductTemplateMappingId(UUID.randomUUID());
            result.setProductTemplateId(productTemplate.getProductTemplateId());
            result.setSettingDataCode(settingDataCode);
        }
        return result;
    }
}
