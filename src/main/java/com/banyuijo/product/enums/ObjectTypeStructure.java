package com.banyuijo.product.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ObjectTypeStructure {
    OBJECT_TYPE1 (1, "%s", null, null),
    OBJECT_TYPE2 (2, null, "%s", "%s"),
    OBJECT_TYPE3 (3, "%s", "%s", "%s");

    private final int code;
    private final String value;
    private final String lowerBond;
    private final String upperBond;
}
