package com.banyuijo.foundation.dto.product.site.structure.edit;

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
public class SiteProductEditStructureInput {
    private List<SiteBaseProductStructure> structures;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class SiteBaseProductStructure {
        private String siteBaseProductStructureName;

        private Integer seq;

        private List<SiteBaseProductSetting> settings;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class SiteBaseProductSetting {

            private UUID siteBaseProductSettingTypeId;

            private UUID settingTypeId;

            private List<SiteBaseProductSettingData> settingData;

            @AllArgsConstructor
            @NoArgsConstructor
            @Setter
            @Getter
            public static class SiteBaseProductSettingData {
                private UUID siteBaseProductSettingDataId;

                private UUID siteBaseProductSettingId;

                private Integer seq;

                private Integer object;
            }
        }
    }
}
