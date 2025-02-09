package com.iconnect.product.dto.product.site.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductTemplateOutput {
    private UUID siteProductId;
    private List<StructureDTO> structures;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class StructureDTO {
        private int seq;
        private String siteBaseProductStructureName;
        private List<CardTemplateDTO> cardTemplate;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class CardTemplateDTO {
            private int seq;
            private String componentValue;
            private String settingCode;
            //remove
            private String value = "%s";
            private String upperBond = "%s";
            private String lowerBond = "%s";
        }
    }
}
