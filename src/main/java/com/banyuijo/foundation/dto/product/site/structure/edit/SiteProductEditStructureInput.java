package com.banyuijo.foundation.dto.product.site.structure.edit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductEditStructureInput {
    private HashMap<Integer, String> structure;
}
