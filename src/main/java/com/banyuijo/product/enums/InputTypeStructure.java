package com.banyuijo.product.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InputTypeStructure {
    INPUT_TYPE1 (1, "%s", "%s", "%s", "input, input, input"),
    INPUT_TYPE2 (2, "%s", "%s", "%s", "display, display, input"),
    INPUT_TYPE3 (3, "%s", "%s", "%s", "input, input, display"),
    INPUT_TYPE4 (4, "%s", null, null, "input, null, null"),
    INPUT_TYPE5 (5, "%s", null, null, "display, null, null"),
    INPUT_TYPE6 (6, null, "%s", "%s", "null, input, input"),
    INPUT_TYPE7 (7, null, "%s", "%s", "null, display, display"),
    INPUT_TYPE8 (8, null, "%s", "%s", "null, input, display"),
    INPUT_TYPE9 (9, null, "%s", "%s", "null, display, input");

    private final int code;
    private final String value;
    private final String lowerBond;
    private final String upperBond;
    private final String description;
}
