package com.iconnect.product.dto.structuretype.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StructureTypeObjectOutput {
    private int code;
    private String value;
    private String lowerBond;
    private String upperBond;
}
