package com.banyuijo.product.enums;

import com.banyuijo.product.exception.HttpStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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

    public static ObjectTypeStructure fromCode(Integer code) {
        return Arrays.stream(values())
                .filter(value -> value.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new HttpStatusException(HttpStatusCode.INVALID_DATA_TYPE, "Object Type"));
    }
}
