package com.banyuijo.product.service.product.site.structure.detail;

import com.banyuijo.product.dto.product.site.structure.SiteProductStructure;
import com.banyuijo.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.banyuijo.product.service.product.site.validator.SiteProductValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductDetailStructureServiceImpl implements SiteProductDetailStructureService {
    private final SiteProductValidator siteProductValidator;
    @Override
    public SiteProductDetailStructureOutput getSiteProductStructureDetail(UUID siteProductId) throws JsonProcessingException {
        siteProductValidator.validateSiteProductId(siteProductId);

        SiteProductStructure structure = build(siteProductId);
        SiteProductDetailStructureOutput output = new SiteProductDetailStructureOutput();
        output.setSiteProductId(siteProductId);
        output.setStructure(structure);
        output.setJsonStructure(structure.toString());
        return output;
    }
    private SiteProductStructure build(UUID siteProductId) {
        SiteProductStructure output = new SiteProductStructure();

        List<SiteProductStructure.SiteBaseProductStructure> siteBaseProductStructures = buildStructure(siteProductId);

        output.setSiteBaseProductParentName("Example Parent Name");
        output.setStructures(siteBaseProductStructures);

        return output;
    }

    private List<SiteProductStructure.SiteBaseProductStructure> buildStructure(UUID siteProductId) {

        SiteProductStructure.SiteBaseProductStructure structure1 = new SiteProductStructure.SiteBaseProductStructure();
        structure1.setSeq(1);
        structure1.setSiteBaseProductStructureName("Structure 1");

        SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData setting1 = new SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData();
        setting1.setSeq(1);
        setting1.setValue("Value 1");
        setting1.setUpperBond("Upper Bond 1");
        setting1.setLowerBond("Lower Bond 1");

        SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData setting2 = new SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData();
        setting2.setSeq(2);
        setting2.setValue("Value 2");
        setting2.setUpperBond("Upper Bond 2");
        setting2.setLowerBond("Lower Bond 2");

        structure1.setSettings(List.of(setting1, setting2));

        // Create a second structure for demonstration
        SiteProductStructure.SiteBaseProductStructure structure2 = new SiteProductStructure.SiteBaseProductStructure();
        structure2.setSeq(2);
        structure2.setSiteBaseProductStructureName("Structure 2");

        SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData setting3 = new SiteProductStructure.SiteBaseProductStructure.SiteBaseProductSettingData();
        setting3.setSeq(1);
        setting3.setValue("Value 3");
        setting3.setUpperBond("Upper Bond 3");
        setting3.setLowerBond("Lower Bond 3");

        structure2.setSettings(List.of(setting3));

        return List.of(structure1, structure2);
    }
}
