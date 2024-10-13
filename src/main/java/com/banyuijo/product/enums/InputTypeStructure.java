package com.banyuijo.product.enums;

import com.banyuijo.product.exception.HttpStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum InputTypeStructure {
    INPUT_TYPE1 (1, "%s", "%s", "%s", "input, input, input"),
    INPUT_TYPE2 (2, "%s", "%s", "%s", "input, display, display"),
    INPUT_TYPE3 (3, "%s", "%s", "%s", "display, input, input"),
    INPUT_TYPE4 (3, "%s", "%s", "%s", "display, display, display"),
    INPUT_TYPE5 (4, "%s", null, null, "input, null, null"),
    INPUT_TYPE6 (5, "%s", null, null, "display, null, null"),
    INPUT_TYPE7 (6, null, "%s", "%s", "null, input, input"),
    INPUT_TYPE8 (7, null, "%s", "%s", "null, display, display"),
    INPUT_TYPE9 (8, null, "%s", "%s", "null, input, display"),
    INPUT_TYPE10 (9, null, "%s", "%s", "null, display, input");

    private final int code;
    private final String value;
    private final String lowerBond;
    private final String upperBond;
    private final String description;

    public static InputTypeStructure fromCode(Integer code) {
        return Arrays.stream(values())
                .filter(value -> value.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new HttpStatusException(HttpStatusCode.INVALID_DATA_TYPE, "Object Type"));
    }
}
