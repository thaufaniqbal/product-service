package com.iconnect.product.dto.product.site.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductStructureWrapper {

    private UUID siteBaseProductParentId;

    private String siteBaseProductParentName;

    private UUID siteProductId;

    private UUID productTypeId;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastUpdatedBy;

    private LocalDateTime lastUpdatedDate;

    private List<SiteBaseProductStructure>structures;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class SiteBaseProductStructure {
        private UUID siteBaseProductStructureId;

        private String siteBaseProductStructureName;

        private UUID siteBaseProductParentId;

        private Integer seq;

        private String createdBy;

        private LocalDateTime createdDate;

        private String lastUpdatedBy;

        private LocalDateTime lastUpdatedDate;

        private List<SiteBaseProductSetting> settings;
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class SiteBaseProductSetting {

            private UUID siteBaseProductSettingId;

            private UUID siteBaseProductSettingTypeId;

            private UUID siteBaseProductStructureId;

            private String createdBy;

            private LocalDateTime createdDate;

            private String lastUpdatedBy;

            private LocalDateTime lastUpdatedDate;

            private List<SiteBaseProductSettingData> settingData;

            @AllArgsConstructor
            @NoArgsConstructor
            @Setter
            @Getter
            public static class SiteBaseProductSettingData {
                private UUID siteBaseProductSettingDataId;

                private UUID siteBaseProductSettingId;

                private Integer seq;

                private String value;

                private String upperBond;

                private String lowerBond;

            }
        }
    }

}
