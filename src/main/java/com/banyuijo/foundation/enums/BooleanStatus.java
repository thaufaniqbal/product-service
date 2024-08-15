package com.banyuijo.foundation.enums;

import com.banyuijo.foundation.exception.HttpStatusException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BooleanStatus {
    YES(-1, "Yes", true),
    NO(0, "No", false);

    private final int code;
    private final String description;
    private final Boolean booleanStatus;
    public static BooleanStatus fromCode(Integer code) {
        return Arrays.stream(values())
                .filter(value -> value.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new HttpStatusException(HttpStatusCode.INVALID_DATA_TYPE, "Boolean Status"));
    }

}
