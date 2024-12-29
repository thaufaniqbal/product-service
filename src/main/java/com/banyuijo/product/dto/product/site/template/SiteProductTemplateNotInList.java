package com.banyuijo.product.dto.product.site.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductTemplateNotInList {
    private String siteProductId;
    private List<SiteProductTemplateInput.StructureDTO> structures;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class StructureDTO {
        private int seq;
        private String siteBaseProductStructureName;
        private List<SiteProductTemplateInput.StructureDTO.CardTemplateDTO> cardTemplate;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public class CardTemplateDTO {
            private String settingCode;
        }
    }
}
