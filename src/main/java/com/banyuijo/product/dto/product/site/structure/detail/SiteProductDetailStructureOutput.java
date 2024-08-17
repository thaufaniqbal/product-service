package com.banyuijo.product.dto.product.site.structure.detail;

import com.banyuijo.product.dto.product.site.structure.SiteProductStructureJson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductDetailStructureOutput {
    private UUID siteProductId;
    private SiteProductStructureJson structure;
    private String jsonStructure;
}
