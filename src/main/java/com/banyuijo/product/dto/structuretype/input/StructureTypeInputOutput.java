package com.banyuijo.product.dto.structuretype.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StructureTypeInputOutput {
    private int code;
    private String value;
    private String lowerBond;
    private String upperBond;
    private String description;
}
