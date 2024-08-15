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
    private HashMap<Integer, String> structure;
}
