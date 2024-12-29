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
public class SiteProductTemplateInput {
    private List<StructureDTO> structures;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class StructureDTO {
        private int seq;
        private List<CardTemplateDTO> cardTemplate;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public class CardTemplateDTO {
            private int seq;
            private String componentValue;
            private String settingCode;
        }
    }
}
