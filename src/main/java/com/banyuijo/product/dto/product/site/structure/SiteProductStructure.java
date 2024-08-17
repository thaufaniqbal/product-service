package com.banyuijo.product.dto.product.site.structure;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SiteProductStructure {

    private String siteBaseProductParentName;

    private List<SiteBaseProductStructure>structures;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    public static class SiteBaseProductStructure {

        private Integer seq;

        private String siteBaseProductStructureName;

        private List<SiteBaseProductSettingData> settings;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        @ToString
        public static class SiteBaseProductSettingData {

            private Integer seq;

            private String value;

            private String upperBond;

            private String lowerBond;

        }
    }
}


