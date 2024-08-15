package com.banyuijo.foundation.dto.product.site.structure.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductDetailStructureOutput {
    private UUID siteProductId;
    private HashMap<Integer, StructureProduct> structure;
    private String jsonStructure;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class StructureProduct {
        private String structureName;
        private String dataType;
        private Integer dataStructureType;
    }
}
