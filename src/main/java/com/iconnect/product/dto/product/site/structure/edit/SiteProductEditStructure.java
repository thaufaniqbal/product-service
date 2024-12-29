package com.iconnect.product.dto.product.site.structure.edit;

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
public class SiteProductEditStructure {
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
            private Integer seq;

            private Integer object;

            private Integer input;

            private String settingCode;

            private String inputTypeDescription;

            private String objectName;

            private UUID siteBaseProductSettingTypeId;
        }

    }
}