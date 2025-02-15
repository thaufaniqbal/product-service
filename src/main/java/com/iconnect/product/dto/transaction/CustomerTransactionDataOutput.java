package com.iconnect.product.dto.transaction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class CustomerTransactionDataOutput {
    @JsonDeserialize(using = com.fasterxml.jackson.databind.deser.std.UUIDDeserializer.class)
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
            private String value;
            private String upperBond;
            private String lowerBond;
            private String description;
        }
    }
}
